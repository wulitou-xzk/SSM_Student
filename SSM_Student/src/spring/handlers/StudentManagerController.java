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
	
	/*================================��ȡservice����spring�����ṩ��=====================================*/
	
	@Autowired(required=false)
	private IStudentService stuservice;		// ѧ��������Ϣ
	@Autowired()
	private ICollegesService collservice;   // ѧԺ
	@Autowired()
	private IMajorsService majorservice;    // ��ѧרҵ
	@Autowired()
	private ISCMService scmservice;         // ѧԺ��רҵ����
	@Autowired()
	private IUserService userservice;       // �û������ѧ��ʱ�Զ����ѧ���˺ţ�
	@Autowired()
	private ISCService scservice;           // ѧ����γ̹���
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
	
	/*================================����ѧ����ѡ�޵Ŀγ�=====================================*/
	
	// ��ѧ����ѡ�γ̽�����ӡ����¹���type=0����ӣ�type=1�����£�
	@RequestMapping("/add_updateSC.do")
	public String addOrupdateSC(String msg,String sId,String id,String score,String cname,Model model) {
		int sid = Integer.valueOf(sId);
		double newscore = Double.valueOf(score);
		Courses course = scservice.findCourseByName(cname);
		if(course != null) { // ���ڸÿγ̣��������ز���
			scservice.addOrupdateSC(Integer.valueOf(id),sid,course.getId(),0,newscore);
		} else {
			model.addAttribute("courseError", "�����ڸÿγ�");
		}
		return msg.equals("all") ? head+"/manage_stu.jsp" : head+"/manage_onestu.jsp";
	}
	
	// ��ȡĳ��ѧ���Ŀγ���Ϣ���ɼ�
	@RequestMapping("/findSC.do")
	public String findSC(String number, String type, String sid,String sname,Model model) {
		List<Student_Course> sc = scservice.findRevisedCourses(Integer.valueOf(sid));
		if(sc.size() <= 0) {
			model.addAttribute("nullRevise", sname+"��δѡ��");
		} else {
			model.addAttribute("sname", sname+" �Ŀγ̼���Ӧ�ɼ�");
			model.addAttribute("sc", sc);
		}
		model.addAttribute("number", number);
		model.addAttribute("stuId", sid);
		model.addAttribute("type", type);
		model.addAttribute("sname", sname);
		return head+"/course_score.jsp";
	}
	
	// ɾ��ѧ����ѡ��ĳ�ſγ�
	@RequestMapping("/delSC.do")
	public String delSC(String sid, String cname) {
		Courses course = scservice.findCourseByName(cname);
		scservice.removeSCBySidCid(Integer.valueOf(sid), course.getId());
		return head+"/manage_stu.jsp";
	}
	
	/*================================����ѧ����Ϣ=====================================*/
	
	// ��ȡ����ѧ����Ϣ
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
					request.setAttribute(student.getId()+"Error", student.getSname()+"δ����ѧԺ");
				}
			}
			
			request.getSession().setAttribute("totalPage", result[1]);	// ��ҳ��
			request.getSession().setAttribute("colId", collId);	
			request.getSession().setAttribute("begin", result[2]);		// ��ǰҳ
			request.getSession().setAttribute("students", students);	// ѧ��
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
	
	// ��ѯ����ѧ����Ϣ
	@RequestMapping("/findStudent.do")
	public String findStudentByNumber(String number,HttpServletRequest request) {
		Student student = stuservice.findStudentByNumber(number);
		if (!Pattern.matches("^\\d{10}$", number) || student == null) {
			request.setAttribute("numberError", "�����ڸ�ѧ��");
			return head + "/manage_stu.jsp";
		} 
		int err = findColAndMaj(student);
		if(err == 1)
			request.setAttribute("scmError", student.getSname()+"ѧԺ��רҵ����");
		student.setGender(student.getGender().equals("M") ? "��":"Ů");
		request.setAttribute("student", student);
		return head + "/manage_onestu.jsp";
	}
	
	// ��ѯѧ������ѧԺ����ѧרҵ
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
	
	// ɾ��һ��ѧ������ǰ�˻�ȡѧ��ѧ�ż���id��
	@RequestMapping("/delStudent.do")
	public String expelStudent(String number,String id) {
		scmservice.expelSCM(number);
		scservice.expelSC(Integer.valueOf(id));
		stuservice.expelStudent(number);
		userservice.removeUser(number);
		return "redirect:findAll.do";
	}
	
	// ��ӻ����ѧ��
	@RequestMapping("/add_updateStudent.do")
	public String addOrupdateStudent(String type, String sname, String number, String gender, String birth, String grader, String college,String major,Model model){
		Student student = new Student(sname,number,gender,null,grader);
		model.addAttribute("student", student);
		int err[] = new int[3];
		err = checkStuMsg(type,student,model,birth,college,major);
		if (err[0] == 0) { // err[0]=0��ʾ��Ϣ�޴���ִ����ز���
			SCM scm = new SCM(number,err[1],err[2]);
			if(type.equals("0")) {	// typeΪ0ʱ��ʾ��Ӳ���
				stuservice.addStudent(student);
				User test = userservice.checkUser(new User("test00","test00"));
				userservice.addOrUpdateUser((new User(number,number,test.getType())));
				scmservice.addSCM(scm);
			} else {	// typeΪ1ʱ��ʾ���²���
				stuservice.updateStudent(student);
				scmservice.updateSCM(scm);
			}
			return "redirect:findAll.do"; // ��ӡ����²�����ɺ󷵻�����ѧ����Ϣ
		}
		// err[0]!=0����ʾ�������Ϣ���󣬽�����ԭҳ�� 
		model.addAttribute("mmm", major);
		model.addAttribute("ccc", college);
		return type.equals("0") ? head+"/add_student.jsp" : head+"/manage_stu.jsp";
	}
	
	// ��������Ƿ���ȷ���Ƿ���ϸ�ʽ
	public int[] checkStuMsg(String type, Student student, Model model, String birth, String college, String major) {
		Date date = null;
		int err[] = {0,0,0};
		int colId = collservice.exitCollege(college);
		Majors m = majorservice.exitMajor(major);
		if(student.getSname().equals("")) {
			err[0]++;
			model.addAttribute("nameError", "������д����");
		}
		if(!Pattern.matches("^[��|Ů]$", student.getGender()) || student.getGender().equals("")) {
			err[0]++;
			model.addAttribute("genderError", "�Ա���д����");
		}
		if(type.equals("0") && (stuservice.checkStudent(student) || student.getNumber().length() != 10)) {
			err[0]++;
			model.addAttribute("numError", "ѧ���Ѵ��ڻ���д����");
		}
		if(!Pattern.matches("^\\d{4}$", student.getGrader()) || student.getGrader().equals("")) {
			err[0]++;
			model.addAttribute("graderError", "�꼶��д����");
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
			model.addAttribute("birthError", "�������ڴ���");
		}
		if(colId == -1) { // ѧԺ�������򷵻�false
			err[0]++;
			model.addAttribute("collError", "ѧԺ�������");
		}
		if(m == null || m.getCollegeId() != colId) { // רҵ�������򷵻�false
			err[0]++;
			model.addAttribute("majorError", "רҵ��ѧԺ��ƥ��");
		}
		if(err[0] == 0) {
			err[1] = colId;
			err[2] = m.getId();
			student.setGender(student.getGender().equals("��") ? "M" : "F");
		}
		model.addAttribute("birth", birth);
		return err;
	}
}