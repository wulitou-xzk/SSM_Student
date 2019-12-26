package spring.handlers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.beans.Student;
import spring.beans.Teach_course;
import spring.beans.Teachers;
import spring.check.PagesUtils;
import spring.service.ISCService;
import spring.service.IStudentService;
import spring.service.ITCService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired(required=false)
	private ITCService tcservice;
	@Autowired()
	private ISCService scservice;
	@Autowired()
	private IStudentService stuservice;
	public void setTcservice(ITCService tcservice) {
		this.tcservice = tcservice;
	}
	public void setScservice(ISCService scservice) {
		this.scservice = scservice;
	}
	public void setStuservice(IStudentService stuservice) {
		this.stuservice = stuservice;
	}
	
	String head = "/pages/jsp/teacher";
	
	// 教师查询自己需要上的课程（tid在教师登录时存放在Session中）
	@RequestMapping("/findCoursesByTid.do")
	public String findCourseByTid(HttpServletRequest request) {
		Teachers teacher = (Teachers) request.getSession().getAttribute("person");
		int tid = teacher.getId();
		List<Teach_course> tccourses = tcservice.findCourseByTid(tid);
		if(tccourses.size() == 0) {
			request.setAttribute("noteach", "您暂无授课课程");
		}
		request.getSession().setAttribute("tccourses", tccourses);
		return head+"/teach_course.jsp"; // 返回教师查看课程页面
	}
	
	// 查找选修了某课程的学生（班级）
	@RequestMapping("/findStudents.do")
	public String findStudents(String fbegin, String cid, HttpServletRequest request) {
		Teachers teacher = (Teachers) request.getSession().getAttribute("person");
		int teachId = teacher.getId();
		int courseId = Integer.valueOf(cid);
		int count = stuservice.findStudentByTidCid(teachId, courseId,0).size();
		int result[] = new PagesUtils(count, fbegin).getResult();
		List<Student> students = stuservice.findStudentByTidCid(teachId,courseId,(result[2] - 1) * result[0]);
		if(students.size() == 0) {
			request.setAttribute("zeroStudent", "暂无学生选修此课程");
		} 
		
		request.setAttribute("cid", cid);
		request.getSession().setAttribute("ftotalPage", result[1]);
		request.getSession().setAttribute("fbegin", result[2]);
		request.getSession().setAttribute("students", students);
		return head+"/class.jsp";  // 返回学生查看页面（班级）
	}
	
	// 更改学生成绩 / 将学生从班级删除
	// 更改成绩：stu_course；删除学生：teach_course
	@RequestMapping("/delOrupdate.do")
	public String delOrupdateStudent(String sid, String cid, String score, HttpServletRequest request) {
		Teachers teacher = (Teachers) request.getSession().getAttribute("person");
		int teachId = teacher.getId();
		int stuId = Integer.valueOf(sid);	// 学生ID
		int courseId = Integer.valueOf(cid);	// 课程ID
		if(score.equals("") || score == null) {
			Teach_course tc = tcservice.findCourseByTidCid(teachId, courseId);
			tc.setNumber(tc.getNumber() - 1);
			scservice.removeSCBySidCid(stuId, courseId);
			tcservice.updateTCNumber(tc);
		} else {
			double sco = Double.valueOf(score);	// 对应成绩
			scservice.addOrupdateSC(0, stuId, courseId, 0, sco);
		}
		return "redirect:findStudents.do?cid="+cid;
	}
	
	// 添加学生至班级
	@RequestMapping("/addStudent.do")
	public String addStudent(String sname, String number, String cid, HttpServletRequest request) {
		Teachers teacher = (Teachers) request.getSession().getAttribute("person");
		int teachId = teacher.getId();
		int couId = Integer.valueOf(cid);
		Student student = stuservice.findStudentByNumber(number);
		Teach_course tc = tcservice.findCourseByTidCid(teachId, couId);
		int err = exitMsg(tc, student, cid, sname, request);
		if(err == 0) {
			int stuId = student.getId();
			scservice.addOrupdateSC(0, stuId, couId, teachId, 0);
			tc.setNumber(tc.getNumber() + 1);
			tcservice.updateTCNumber(tc);
			return "redirect:findStudents.do?cid="+cid;
		} else {
			return head+"/class.jsp";
		}
	}
	
	public int exitMsg(Teach_course tc, Student student, String cid, String sname, HttpServletRequest request) {
		int err = 0;
		if(student == null) {
			err++;
			request.setAttribute("numError", "学号不存在");
		}
		if(sname.equals("") || sname == null || !student.getSname().equals(sname)) {
			err++;
			request.setAttribute("snameError", "姓名输入错误");
		}
		if (student != null && scservice.getSCBySidCid(student.getId(), Integer.valueOf(cid)) != null) {
			err++;
			request.setAttribute("revised", sname+"已选修此课程");
		}
		if(tc.getNumber() >= tc.getMaxnumber()) {
			err++;
			request.setAttribute("fullError", "班级人数已满");
		}
		request.setAttribute("cid", cid);
		request.setAttribute("sname", sname);
		request.setAttribute("number", student.getNumber());
		return err;
	}
	
	@RequestMapping("/person.do")
	public String person(HttpServletRequest request) {
		Teachers person = (Teachers) request.getSession().getAttribute("person");
		person.setT_gender(person.getT_gender().equals("M") ? "男" : "女");
		return "/pages/jsp/teacher/personMsg.jsp";
	}
	
}
