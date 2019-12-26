package spring.handlers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.beans.Courses;
import spring.beans.Teach_course;
import spring.beans.User;
import spring.check.PagesUtils;
import spring.service.ICollegesService;
import spring.service.ISCService;
import spring.service.ITCService;
import spring.service.ITeachersService;
import spring.service.IUserService;

@Controller
@RequestMapping("/course")
public class CourseManagerController {
	
	@Autowired(required=false)
	private ISCService scservice;
	@Autowired()
	private IUserService userservice;
	@Autowired()
	private ICollegesService colservice;
	@Autowired()
	private ITCService tcservice;
	@Autowired()
	private ITeachersService tservice;
	public void setScservice(ISCService scservice) {
		this.scservice = scservice;
	}
	public void setUserservice(IUserService userservice) {
		this.userservice = userservice;
	}
	public void setColservice(ICollegesService colservice) {
		this.colservice = colservice;
	}
	public void setTcservice(ITCService tcservice) {
		this.tcservice = tcservice;
	}
	public void setTservice(ITeachersService tservice) {
		this.tservice = tservice;
	}

	private String head1 = "/pages/jsp/manage";
	private String head2 = "/pages/jsp/student";
	
	// ͨ����ʦId���Ʋ���ѧԺID
	@RequestMapping("/findByCollege.do")
	public String findCourseByCoId(String type, String college, String tid, String tname, HttpServletRequest request) {
		int coId = tservice.findByTid(Integer.valueOf(tid)).getCollId();
		college = (college == null) ? colservice.findCollById(coId).getCollege() : college;
		List<Courses> courses = scservice.findCourseByCoId(coId);
		if(courses.size() == 0) {
			request.setAttribute("nullCourse", college+"ѧԺ��δ����");
		} else {
			request.setAttribute("college", college);
			request.setAttribute("courses", courses);
		}
		request.setAttribute("tid", tid);
		request.setAttribute("tname", tname);
		request.setAttribute("type", type);
		return head1+"/add_tc.jsp";
	}

	// ���»�����ӿγ�
	@RequestMapping("/addOrupCourse.do") 
	public String addOrupCourse(String cid, String cname, String college, Model model) {
		Courses courses = null;
		if(cname == null || cname.equals("") || college.equals("") || college == null) {
			model.addAttribute("nullError", "�������ݲ���Ϊ��");
			return head1+"/manage_course.jsp";
		}
		int colId = colservice.findCollByName(college).getId();
		if(cid == null) { // cidΪnull��ʾ������Ӳ���,��Ϊnull���ʾ���¿γ���Ϣ
			if(colId < 1) {
				model.addAttribute("collegeError", "ѧԺ��Ϣ����");
				return head1+"/manage_course.jsp";  //  ���ʧ�ܣ����ؿγ����ҳ��
			}
			if(scservice.findCourseByName(cname) == null) {
				courses = new Courses(0,cname,0,colId);
			} else {
				model.addAttribute("courseError", cname+"�Ѵ���");
				return head1+"/manage_course.jsp";  //  ���ʧ�ܣ����ؿγ����ҳ��
			}
		} else {    // cid��Ϊnull���ʾ���¿γ���Ϣ
			courses = new Courses(Integer.valueOf(cid),cname,0,colId);
		}
		scservice.addOrupdateCourse(courses);
		return "redirect:findAllCourses.do";	// ���ؿγ̹���ҳ��
	}
	
	// ɾ��һ���γ�
	@RequestMapping("/delCourse.do")
	public String delCourse(String cid) {
		scservice.deleteSCByCid(Integer.valueOf(cid));
		scservice.deleteCourseById(Integer.valueOf(cid));
		tcservice.deleteTCByCid(Integer.valueOf(cid));
		return "redirect:findAllCourses.do"; // ���ؿγ̹������
	}
	
	// ��ȡ���пγ���Ϣ
	@RequestMapping("/findAllCourses.do")
	public String findAllCourses(String cbegin, HttpServletRequest request) {
		if(request.getSession().getAttribute("colleges") == null) {
			request.getSession().setAttribute("colleges", colservice.findAllColl());
		}
		int count = scservice.CountSize();
		int result[] = new PagesUtils(count, cbegin).getResult();
		List<Courses> courses = scservice.finCourseByPage((result[2] - 1) * result[0]);
		User user = (User) request.getSession().getAttribute("user");
		int clock = userservice.countClock();
		if(clock > 0) {	// ����0���ʾ�ѹر�ѡ��
			request.setAttribute("clock", clock);
		}
		request.getSession().setAttribute("ctotalPage", result[1]);
		request.getSession().setAttribute("cbegin", result[2]);
		request.getSession().setAttribute("courses", courses);
		return user.getType() > 0 ? head1+"/manage_course.jsp" : head2+"/revise.jsp"; // �����û����;��������ĸ�ҳ��
	}
	
	// �������п�ѡ��Ŀγ�
	@RequestMapping("/reviceCourse.do")
	public String findAllReviseCourse(String rbegin, HttpServletRequest request) {
		int count = tcservice.findAllTC().size();
		if(count == 0) {
			request.setAttribute("zeroRevise", "���޿ɹ�ѡ��Ŀγ�");
		} else {
			int result[] = new PagesUtils(count, rbegin).getResult();
			List<Teach_course> reviseTC = tcservice.findTCByPage((result[2] - 1) * result[0]);
			for (Teach_course tc : reviseTC) {
				tc.setDay(turnWeek(tc.getDay()));
			}
			request.getSession().setAttribute("rtotalPage", result[1]);
			request.getSession().setAttribute("rbegin", result[2]);
			request.getSession().setAttribute("reviseTC", reviseTC);
		}
		return head2+"/revise.jsp";
	}
	
	public String turnWeek(String day) {
		String chi[] = {"����һ","���ڶ�","������","������","������","������","������"};
		return chi["MoTuWeThFrSaSu".indexOf(day.substring(0, 2)) / 2];
	}
	
	// ����/�ر�ѡ�����пγ�
	@RequestMapping("/onOrOffAll.do")
	public String onOrOffAll(String clock, HttpServletRequest request) {
		int lock = Integer.valueOf(clock);
		scservice.onOrOffAllLock(lock);
		if(clock.equals("0")) {	// �ر�
			userservice.setType(-1);
		} else {  // ����
			userservice.setType(0);
		}
		return "redirect:findAllCourses.do";
	}
	
	// ����/�رյ����γ�
	@RequestMapping("/onOrOffOne.do")
	public String onOroffCourse(String cid, String clock, HttpServletRequest request) {
		scservice.onOroffLock(Integer.valueOf(cid),Integer.valueOf(clock));
		return "redirect:findAllCourses.do";
	}

}
