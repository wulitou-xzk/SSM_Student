package spring.handlers;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.beans.Courses;
import spring.beans.Majors;
import spring.beans.SCM;
import spring.beans.Student;
import spring.beans.Student_Course;
import spring.beans.User;
import spring.check.MyDatdaCheck;
import spring.check.PagesUtils;
import spring.service.ICollegesService;
import spring.service.IMajorsService;
import spring.service.ISCMService;
import spring.service.ISCService;
import spring.service.IStudentService;
import spring.service.IUserService;

@Controller
@RequestMapping("/studentmanage")
public class StudentManagerController {
	
	/*================================获取service（由spring容器提供）=====================================*/
	
	@Autowired(required=false)
	private IStudentService stuservice;		// 学生个人信息
	@Autowired()
	private ICollegesService collservice;   // 学院
	@Autowired()
	private IMajorsService majorservice;    // 所学专业
	@Autowired()
	private ISCMService scmservice;         // 学院与专业关联
	@Autowired()
	private IUserService userservice;       // 用户（添加学生时自动添加学生账号）
	@Autowired()
	private ISCService scservice;           // 学生与课程关联
	public void setScservice(ISCService scservice) {
		this.scservice = scservice;
	}
	public void setStuervice(IStudentService stuservice) { 
		this.stuservice = stuservice;
	}
	public void setCollservice(ICollegesService collservice) {
		this.collservice = collservice;
	}
	public void setMajorservice(IMajorsService majorservice) {
		this.majorservice = majorservice;
	}
	public void setScmservice(ISCMService scmservice) {
		this.scmservice = scmservice;
	}
	public void setUserservice(IUserService userservice) {
		this.userservice = userservice;
	}
	
	private String head = "/pages/jsp/manage";
	
	/*================================管理学生已选修的课程=====================================*/
	
	// 对学生所选课程进行添加、更新管理（type=0，添加；type=1，更新）
	@RequestMapping("/add_updateSC.do")
	public String addOrupdateSC(String msg,String sId,String id,String score,String cname,Model model) {
		int sid = Integer.valueOf(sId);
		double newscore = Double.valueOf(score);
		Courses course = scservice.findCourseByName(cname);
		if(course != null) { // 存在该课程，则进行相关操作
			scservice.addOrupdateSC(Integer.valueOf(id),sid,course.getId(),0,newscore);
		} else {
			model.addAttribute("courseError", "不存在该课程");
		}
		return msg.equals("all") ? head+"/manage_stu.jsp" : head+"/manage_onestu.jsp";
	}
	
	// 获取某个学生的课程信息及成绩
	@RequestMapping("/findSC.do")
	public String findSC(String number, String type, String sid,String sname,Model model) {
		List<Student_Course> sc = scservice.findRevisedCourses(Integer.valueOf(sid));
		if(sc.size() <= 0) {
			model.addAttribute("nullRevise", sname+"尚未选课");
		} else {
			model.addAttribute("sname", sname+" 的课程及对应成绩");
			model.addAttribute("sc", sc);
		}
		model.addAttribute("number", number);
		model.addAttribute("stuId", sid);
		model.addAttribute("type", type);
		model.addAttribute("sname", sname);
		return head+"/course_score.jsp";
	}
	
	// 删除学生所选的某门课程
	@RequestMapping("/delSC.do")
	public String delSC(String sid, String cname) {
		Courses course = scservice.findCourseByName(cname);
		scservice.removeSCBySidCid(Integer.valueOf(sid), course.getId());
		return head+"/manage_stu.jsp";
	}
	
	/*================================管理学生信息=====================================*/
	
	// 获取所有学生信息
	@RequestMapping("/findAll.do")
	public String findAllStudents(String colId, String begin, HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		if(request.getSession().getAttribute("colleges") == null) {
			request.getSession().setAttribute("colleges", collservice.findAllColl());
		}
		if(colId != null || begin != null) {
			int collId = Integer.valueOf(colId);
			int count = stuservice.CountSize(collId);
			int result[] = new PagesUtils(count, begin).getResult();
			List<Student> students = stuservice.findByPage(collId, (result[2] - 1) * result[0]);
			for (Student student : students) {
				int err = findColAndMaj(student);
				if(err == 1) {
					request.setAttribute(student.getId()+"Error", student.getSname()+"未分配学院");
				}
			}
			
			request.getSession().setAttribute("totalPage", result[1]);	// 总页数
			request.getSession().setAttribute("colId", collId);	
			request.getSession().setAttribute("begin", result[2]);		// 当前页
			request.getSession().setAttribute("students", students);	// 学生
		}
		return head+"/manage_stu.jsp";
	}
	
	@RequestMapping("/findMajorByCollege.do")
	public String getColMaj(String colId, HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		int collegeId = Integer.valueOf(colId);
		List<Majors> majors = majorservice.findByColId(collegeId);
		request.setAttribute("majors", majors);
		request.setAttribute("ccc", collservice.findCollById(collegeId).getCollege());
		return head + "/add_student.jsp";
	}
	
	// 查询单个学生信息
	@RequestMapping("/findStudent.do")
	public String findStudentByNumber(String number,HttpServletRequest request) {
		Student student = stuservice.findStudentByNumber(number);
		if (!Pattern.matches("^\\d{10}$", number) || student == null) {
			request.setAttribute("numberError", "不存在该学号");
			return head + "/manage_stu.jsp";
		} 
		int err = findColAndMaj(student);
		if(err == 1)
			request.setAttribute("scmError", student.getSname()+"学院或专业不明");
		student.setGender(student.getGender().equals("M") ? "男":"女");
		request.setAttribute("student", student);
		return head + "/manage_onestu.jsp";
	}
	
	// 查询学生所在学院及所学专业
	public int findColAndMaj(Student student) {
		int err = 0;
		SCM scm = scmservice.findSCMByNumber(student.getNumber());
		if(scm != null) {
			String colName = collservice.findCollById(scm.getColId()).getCollege();
			String majName = majorservice.findMajById(scm.getMajorId()).getMajor();
			student.setCollege(colName);
			student.setMajor(majName);
		} else {
			err = 1;
		}
		return err;
	}
	
	// 删除一个学生（从前端获取学生学号及其id）
	@RequestMapping("/delStudent.do")
	public String expelStudent(String number,String id) {
		scmservice.expelSCM(number);
		scservice.expelSC(Integer.valueOf(id));
		stuservice.expelStudent(number);
		userservice.removeUser(number);
		return "redirect:findAll.do";
	}
	
	// 添加或更新学生
	@RequestMapping("/add_updateStudent.do")
	public String addOrupdateStudent(String type, String sname, String number, String gender, String birth, String grader, String college,String major,Model model){
		Student student = new Student(sname,number,gender,null,grader);
		model.addAttribute("student", student);
		int err[] = new int[3];
		err = checkStuMsg(type,student,model,birth,college,major);
		if (err[0] == 0) { // err[0]=0表示信息无错，可执行相关操作
			SCM scm = new SCM(number,err[1],err[2]);
			if(type.equals("0")) {	// type为0时表示添加操作
				stuservice.addStudent(student);
				User test = userservice.checkUser(new User("test00","test00"));
				userservice.addOrUpdateUser((new User(number,number,test.getType())));
				scmservice.addSCM(scm);
			} else {	// type为1时表示更新操作
				stuservice.updateStudent(student);
				scmservice.updateSCM(scm);
			}
			return "redirect:findAll.do"; // 添加、更新操作完成后返回所有学生信息
		}
		// err[0]!=0，表示输入的信息有误，将返回原页面 
		model.addAttribute("mmm", major);
		model.addAttribute("ccc", college);
		return type.equals("0") ? head+"/add_student.jsp" : head+"/manage_stu.jsp";
	}
	
	// 检查数据是否正确，是否符合格式
	public int[] checkStuMsg(String type, Student student, Model model, String birth, String college, String major) {
		Date date = null;
		int err[] = {0,0,0};
		int colId = collservice.exitCollege(college);
		Majors m = majorservice.exitMajor(major);
		if(student.getSname().equals("")) {
			err[0]++;
			model.addAttribute("nameError", "姓名填写错误");
		}
		if(!Pattern.matches("^[男|女]$", student.getGender()) || student.getGender().equals("")) {
			err[0]++;
			model.addAttribute("genderError", "性别填写错误");
		}
		if(type.equals("0") && (stuservice.checkStudent(student) || student.getNumber().length() != 10)) {
			err[0]++;
			model.addAttribute("numError", "学号已存在或填写错误");
		}
		if(!Pattern.matches("^\\d{4}$", student.getGrader()) || student.getGrader().equals("")) {
			err[0]++;
			model.addAttribute("graderError", "年级填写错误");
		}
		if(MyDatdaCheck.getDateFormat(birth) != null) {
			try {
				date = MyDatdaCheck.getDateFormat(birth).parse(birth);
				student.setBirth(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			err[0]++;
			model.addAttribute("birthError", "出生日期错误");
		}
		if(colId == -1) { // 学院不存在则返回false
			err[0]++;
			model.addAttribute("collError", "学院输入错误");
		}
		if(m == null || m.getCollegeId() != colId) { // 专业不存在则返回false
			err[0]++;
			model.addAttribute("majorError", "专业与学院不匹配");
		}
		if(err[0] == 0) {
			err[1] = colId;
			err[2] = m.getId();
			student.setGender(student.getGender().equals("男") ? "M" : "F");
		}
		model.addAttribute("birth", birth);
		return err;
	}
}