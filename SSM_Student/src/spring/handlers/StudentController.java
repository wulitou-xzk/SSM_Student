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
	private IStudentService service;	// 学生
	@Autowired()
	private ISCService scservice;	    // 学生与课程关联
	@Autowired()
	private ITCService tcservice;	// 教师与课程
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

	// 学生选修新课程（学生选课满四科之后将不能再进行选课）
	@RequestMapping("/electiveCourse.do")
	public String electiveCourse(HttpServletRequest request) {
		String att = null;
		String address = null;
		int sid = getStuIdByNumber(request.getParameter("name"));
		int cid = Integer.valueOf(request.getParameter("cid"));
		int tid = Integer.valueOf(request.getParameter("tid"));
		Teach_course course = tcservice.findCourseByTidCid(tid,cid);
		if(!isFull(sid)) {  // 检查学生是否已选修四科
			request.setAttribute("elective", "每次选课至多选修四科");
			return head+"/revise.jsp";
		}
		if (course == null) {	// 判断是否存在学生所选的课程
			att = "课程不存在";
			address = head+"/revise.jsp";
		} else {
			if (isRevised(sid, cid)) {		// 防止学生重复选课
				att = "已选修此课程，不可重复选修";
				address = head+"/revise.jsp";
			} else if (course.getNumber() >= course.getMaxnumber()) {
				att = "选修人数已满";
				address = head+"/revise.jsp";
			} else {
				scservice.addOrupdateSC(0, sid, cid, tid, 0); // 选课后初始成绩默认为0
				course.setNumber(course.getNumber() + 1);
				tcservice.updateTCNumber(course); // 选课成功后更新已选修此课程的人数
				att = "选课成功";
				address = "redirect:findRevised.do";
			} 
		}
		request.setAttribute("elective", att);
		return address;
	}
	
	// 学生选课期间可将已选课程取消。重新选择
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
	
	// 查找学生所有已修的课程及其信息
	@RequestMapping("/findRevised.do")
	public String findRevisedCourses(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		int sid = getStuIdByNumber(user.getUsername());
		List<Student_Course> revisedCourse = scservice.findRevisedCourses(sid);
		if(revisedCourse.size() == 0) {
			request.setAttribute("zeroCourse", "你还未选修任何课程");
		} else {
			request.getSession().setAttribute("cancel", userservice.countClock());
			request.getSession().setAttribute("revisedCourse", revisedCourse);
		}
		request.getSession().setAttribute("sid",sid);
		return head+"/revised_course.jsp";
	}
	
	// 获取学生id
	public int getStuIdByNumber(String number) {
		Student student = service.findStudentByNumber(number);
		int sid = student.getId();
		return sid;
	}
	
	// 判断是否重复选修课程
	public boolean isRevised(int sid,int cid) { // 已选修返回true，反之返回false
		Student_Course sc = scservice.getSCBySidCid(sid,cid);
		return sc != null;
	}
	
	// 检查学生选修科目是否已满四科
	public boolean isFull(int sid) {
		int numCourse = scservice.getRevised(sid);
		return numCourse < 4;
	}
	
	@RequestMapping("/person.do")
	public String person(HttpServletRequest request) {
		Student person = (Student) request.getSession().getAttribute("person");
		person.setGender(person.getGender().equals("M") ? "男" : "女");
		return "/pages/jsp/student/personMsg.jsp";
	}
}
