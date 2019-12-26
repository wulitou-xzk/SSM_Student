 package spring.handlers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.beans.Student;
import spring.beans.Student_Course;
import spring.beans.Teach_course;
import spring.beans.User;
import spring.service.ISCService;
import spring.service.IStudentService;
import spring.service.ITCService;
import spring.service.IUserService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired(required=false)
	private IStudentService service;	// ѧ��
	@Autowired()
	private ISCService scservice;	    // ѧ����γ̹���
	@Autowired()
	private ITCService tcservice;	// ��ʦ��γ�
	@Autowired()
	private IUserService userservice;
	public void setService(IStudentService service) {
		this.service = service;
	}
	public void setScservice(ISCService scservice) {
		this.scservice = scservice;
	}
	public void setTcservice(ITCService tcservice) {
		this.tcservice = tcservice;
	}
	public void setUserservice(IUserService userservice) {
		this.userservice = userservice;
	}

	private String head = "/pages/jsp/student";

	// ѧ��ѡ���¿γ̣�ѧ��ѡ�����Ŀ�֮�󽫲����ٽ���ѡ�Σ�
	@RequestMapping("/electiveCourse.do")
	public String electiveCourse(HttpServletRequest request) {
		String att = null;
		String address = null;
		int sid = getStuIdByNumber(request.getParameter("name"));
		int cid = Integer.valueOf(request.getParameter("cid"));
		int tid = Integer.valueOf(request.getParameter("tid"));
		Teach_course course = tcservice.findCourseByTidCid(tid,cid);
		if(!isFull(sid)) {  // ���ѧ���Ƿ���ѡ���Ŀ�
			request.setAttribute("elective", "ÿ��ѡ������ѡ���Ŀ�");
			return head+"/revise.jsp";
		}
		if (course == null) {	// �ж��Ƿ����ѧ����ѡ�Ŀγ�
			att = "�γ̲�����";
			address = head+"/revise.jsp";
		} else {
			if (isRevised(sid, cid)) {		// ��ֹѧ���ظ�ѡ��
				att = "��ѡ�޴˿γ̣������ظ�ѡ��";
				address = head+"/revise.jsp";
			} else if (course.getNumber() >= course.getMaxnumber()) {
				att = "ѡ����������";
				address = head+"/revise.jsp";
			} else {
				scservice.addOrupdateSC(0, sid, cid, tid, 0); // ѡ�κ��ʼ�ɼ�Ĭ��Ϊ0
				course.setNumber(course.getNumber() + 1);
				tcservice.updateTCNumber(course); // ѡ�γɹ��������ѡ�޴˿γ̵�����
				att = "ѡ�γɹ�";
				address = "redirect:findRevised.do";
			} 
		}
		request.setAttribute("elective", att);
		return address;
	}
	
	// ѧ��ѡ���ڼ�ɽ���ѡ�γ�ȡ��������ѡ��
	@RequestMapping("/removeRevisedCourse.do")
	public String removeRevisedCourse(HttpServletRequest request) {
		int sid = Integer.valueOf(request.getParameter("sid"));
		int cid = Integer.valueOf(request.getParameter("cid"));
		int tid = Integer.valueOf(request.getParameter("tid"));
		Teach_course course = tcservice.findCourseByTidCid(tid,cid);
		scservice.removeSCBySidCid(sid,cid);
		
		course.setNumber(course.getNumber() - 1);
		tcservice.updateTCNumber(course);
		return "redirect:findRevised.do";
	}
	
	// ����ѧ���������޵Ŀγ̼�����Ϣ
	@RequestMapping("/findRevised.do")
	public String findRevisedCourses(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		int sid = getStuIdByNumber(user.getUsername());
		List<Student_Course> revisedCourse = scservice.findRevisedCourses(sid);
		if(revisedCourse.size() == 0) {
			request.setAttribute("zeroCourse", "�㻹δѡ���κογ�");
		} else {
			request.getSession().setAttribute("cancel", userservice.countClock());
			request.getSession().setAttribute("revisedCourse", revisedCourse);
		}
		request.getSession().setAttribute("sid",sid);
		return head+"/revised_course.jsp";
	}
	
	// ��ȡѧ��id
	public int getStuIdByNumber(String number) {
		Student student = service.findStudentByNumber(number);
		int sid = student.getId();
		return sid;
	}
	
	// �ж��Ƿ��ظ�ѡ�޿γ�
	public boolean isRevised(int sid,int cid) { // ��ѡ�޷���true����֮����false
		Student_Course sc = scservice.getSCBySidCid(sid,cid);
		return sc != null;
	}
	
	// ���ѧ��ѡ�޿�Ŀ�Ƿ������Ŀ�
	public boolean isFull(int sid) {
		int numCourse = scservice.getRevised(sid);
		return numCourse < 4;
	}
	
	@RequestMapping("/person.do")
	public String person(HttpServletRequest request) {
		Student person = (Student) request.getSession().getAttribute("person");
		person.setGender(person.getGender().equals("M") ? "��" : "Ů");
		return "/pages/jsp/student/personMsg.jsp";
	}
}
