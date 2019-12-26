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
	
	// 通过教师Id名称查找学院ID
	@RequestMapping("/findByCollege.do")
	public String findCourseByCoId(String type, String college, String tid, String tname, HttpServletRequest request) {
		int coId = tservice.findByTid(Integer.valueOf(tid)).getCollId();
		college = (college == null) ? colservice.findCollById(coId).getCollege() : college;
		List<Courses> courses = scservice.findCourseByCoId(coId);
		if(courses.size() == 0) {
			request.setAttribute("nullCourse", college+"学院还未开课");
		} else {
			request.setAttribute("college", college);
			request.setAttribute("courses", courses);
		}
		request.setAttribute("tid", tid);
		request.setAttribute("tname", tname);
		request.setAttribute("type", type);
		return head1+"/add_tc.jsp";
	}

	// 更新或者添加课程
	@RequestMapping("/addOrupCourse.do") 
	public String addOrupCourse(String cid, String cname, String college, Model model) {
		Courses courses = null;
		if(cname == null || cname.equals("") || college.equals("") || college == null) {
			model.addAttribute("nullError", "输入内容不可为空");
			return head1+"/manage_course.jsp";
		}
		int colId = colservice.findCollByName(college).getId();
		if(cid == null) { // cid为null表示进行添加操作,不为null则表示更新课程信息
			if(colId < 1) {
				model.addAttribute("collegeError", "学院信息错误");
				return head1+"/manage_course.jsp";  //  添加失败，返回课程添加页面
			}
			if(scservice.findCourseByName(cname) == null) {
				courses = new Courses(0,cname,0,colId);
			} else {
				model.addAttribute("courseError", cname+"已存在");
				return head1+"/manage_course.jsp";  //  添加失败，返回课程添加页面
			}
		} else {    // cid不为null则表示更新课程信息
			courses = new Courses(Integer.valueOf(cid),cname,0,colId);
		}
		scservice.addOrupdateCourse(courses);
		return "redirect:findAllCourses.do";	// 返回课程管理页面
	}
	
	// 删除一个课程
	@RequestMapping("/delCourse.do")
	public String delCourse(String cid) {
		scservice.deleteSCByCid(Integer.valueOf(cid));
		scservice.deleteCourseById(Integer.valueOf(cid));
		tcservice.deleteTCByCid(Integer.valueOf(cid));
		return "redirect:findAllCourses.do"; // 返回课程管理界面
	}
	
	// 获取所有课程信息
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
		if(clock > 0) {	// 大于0则表示已关闭选课
			request.setAttribute("clock", clock);
		}
		request.getSession().setAttribute("ctotalPage", result[1]);
		request.getSession().setAttribute("cbegin", result[2]);
		request.getSession().setAttribute("courses", courses);
		return user.getType() > 0 ? head1+"/manage_course.jsp" : head2+"/revise.jsp"; // 根据用户类型决定返回哪个页面
	}
	
	// 查找所有可选择的课程
	@RequestMapping("/reviceCourse.do")
	public String findAllReviseCourse(String rbegin, HttpServletRequest request) {
		int count = tcservice.findAllTC().size();
		if(count == 0) {
			request.setAttribute("zeroRevise", "暂无可供选择的课程");
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
		String chi[] = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
		return chi["MoTuWeThFrSaSu".indexOf(day.substring(0, 2)) / 2];
	}
	
	// 开启/关闭选修所有课程
	@RequestMapping("/onOrOffAll.do")
	public String onOrOffAll(String clock, HttpServletRequest request) {
		int lock = Integer.valueOf(clock);
		scservice.onOrOffAllLock(lock);
		if(clock.equals("0")) {	// 关闭
			userservice.setType(-1);
		} else {  // 开启
			userservice.setType(0);
		}
		return "redirect:findAllCourses.do";
	}
	
	// 开启/关闭单个课程
	@RequestMapping("/onOrOffOne.do")
	public String onOroffCourse(String cid, String clock, HttpServletRequest request) {
		scservice.onOroffLock(Integer.valueOf(cid),Integer.valueOf(clock));
		return "redirect:findAllCourses.do";
	}

}
