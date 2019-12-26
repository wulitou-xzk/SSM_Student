package spring.handlers;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.beans.Colleges;
import spring.beans.Teach_course;
import spring.beans.Teachers;
import spring.beans.User;
import spring.check.PagesUtils;
import spring.service.ICollegesService;
import spring.service.ISCService;
import spring.service.ITCService;
import spring.service.ITeachersService;
import spring.service.IUserService;

@Controller
@RequestMapping("/teachermanage")
public class TeacherManageController {

	@Autowired(required=false)
	private ITeachersService tservice;
	@Autowired()
	private ITCService tcservice;
	@Autowired()
	private ICollegesService colservice;
	@Autowired()
	private IUserService uservice;
	@Autowired()
	private ISCService scservice;
	public void setTservice(ITeachersService tservice) {
		this.tservice = tservice;
	}
	public void setTcservice(ITCService tcservice) {
		this.tcservice = tcservice;
	}
	public void setColservice(ICollegesService colservice) {
		this.colservice = colservice;
	}
	public void setUservice(IUserService uservice) {
		this.uservice = uservice;
	}
	public void setScservice(ISCService scservice) {
		this.scservice = scservice;
	}

	private String head = "/pages/jsp/manage";
	
	/*************************************教师授课信息******************************************/
	
	// 取消授课
	@RequestMapping("/delTC.do")
	public String removeTCById(String id, String tid, String tname) {
		tcservice.delTCById(Integer.valueOf(id));
		return "redirect:findTC.do?tid="+tid+"&tname="+tname;
	}
	
	// 添加或更新授课
	@RequestMapping("/addOrupdateTC.do")
	public String addOrupdateTC(String id, String tname, String tid, String cname, String number,
			String maxnumber, String place, String day, String on, String off, String begin, String end, HttpServletRequest request) {
		int Id = (id == null || id.equals("")) ? 0 : Integer.valueOf(id);
		int err = exitMsgTC(cname, maxnumber, begin, end, on, off, request);
		if(err == 0) {
			int teachId = Integer.valueOf(tid);
			int courseId = scservice.findCourseByName(cname).getId();
			int num = (number == null || number.equals("")) ? 0 : Integer.valueOf(number);
			int max = Integer.valueOf(maxnumber);
			Teach_course tc = new Teach_course(Id, teachId, courseId, num, max, place, turnWeek(0, day), on+"-"+off, begin+"-"+end+"周");
			tcservice.addOrupdateTC(tc);
		}
		return "redirect:findTC.do?tid="+tid+"&tname="+tname;
	}
	
	// 查找授课信息
	@RequestMapping("/findTC.do")
	public String findTCByTid(String type, String tid, String tname, HttpServletRequest request) {
		int teachId = Integer.valueOf(tid);
		List<Teach_course> tc = tcservice.findTCByTid(teachId);
		if(tc.size() > 0) {
			for (Teach_course t : tc) {
				int num[] = turnOnOff(t.getTime(), 1);
				String week = t.getWeek();
				t.setBegin(week.substring(0,week.indexOf("-")));
				t.setEnd(week.substring(week.indexOf("-")+1,week.indexOf("周")));
				t.setDay(turnWeek(1, t.getDay()));
				t.setOn(num[0]+"");
				t.setOff(num[1]+"");
				tname = t.getT_name();
			}
			request.setAttribute("tc", tc);
		}
		request.setAttribute("type", type);
		request.setAttribute("tid", teachId);
		request.setAttribute("tname", tname);
		return head+"/manage_tc.jsp";
	}
	// 转换格式
	public int[] turnOnOff(String time, int type) {
		int num[] = new int[2];
		int one = Integer.valueOf(time.substring(0, time.indexOf("-")));
		int two = Integer.valueOf(time.substring(time.indexOf("-") + 1, time.length()));
		// Jsp->database
		if(type == 1 && (one == two || (two < 5 && (two - one > 1 || two % 2 != 0)))) {		
			one = two = -1;
		}
		num[0] = one;
		num[1] = two;
		return num;
	}
	
	// 转换中英文
	public String turnWeek(int type, String day) {
		String chi[] = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
		String eng[] = {"Mon","Tues","Wednes","Thurs","Fri","Satur","Sun"};
		if(type == 0) {  // 中->英
			return eng["一二三四五六七".indexOf(day.charAt(2))];
		} else {  // 英->中
			return chi["MoTuWeThFrSaSu".indexOf(day.substring(0, 2)) / 2];
		}
	}
	
	// 信息校验
	public int exitMsgTC(String cname, String maxnumber, String begin, String end, String on, String off, HttpServletRequest request) {
		int err = 0;
		if(cname == null || cname.equals("") || scservice.findCourseByName(cname).getId() < 1) {
			err++;
			request.setAttribute("cnameError", "课程名错误");
		}
		if(!Pattern.matches("^[0-9]{1,}$", maxnumber)) {
			err++;
			request.setAttribute("numError", "人数错误");
		}
		if(Integer.valueOf(begin) < 1 || Integer.valueOf(begin) > 15) {
			err++;
			request.setAttribute("weekError", "错误");
		}
		if(Integer.valueOf(end) < Integer.valueOf(begin) || Integer.valueOf(end) > 16) {
			err++;
			request.setAttribute("weekError", "错误");
		}
		if(turnOnOff(on+"-"+off, 0)[0] == -1) {
			err++;
			request.setAttribute("timeError", "输入错误");
		}
		return err;
	}
	
	/*************************************教师个人信息******************************************/
	
	// 根据教师工号查询教师信息
	@RequestMapping("/findTeacher.do")
	public String findTeacherByNumber(String tnumber, Model model) {
		if(tnumber == null || tnumber.equals("") || tnumber.length() > 5) {
			model.addAttribute("tnumberError", "请输入正确教师工号");
			return head+"/manage_teachers.jsp";
		} else {
			Teachers teacher = tservice.findTeacherByNumber(tnumber);
			if(teacher == null) {
				model.addAttribute("tnumberError", "查找教师失败，请确认工号是否无误");
				return head+"/manage_teachers.jsp";
			}
			String gender = teacher.getT_gender().equals("M") ? "男" : "女";
			teacher.setT_gender(gender);
			model.addAttribute("teacher", teacher);
		}
		return head+"/manage_oneteacher.jsp";
	}

	// 根据教师id删除教师
	@RequestMapping("/delTeacher.do")
	public String removeTeacherById(String tid) {
		tservice.delTeacherById(Integer.valueOf(tid));
		return "redirect:findAllTeacher.do";
	}
	
	// 获取所有教师的信息
	@RequestMapping("/findAllTeacher.do")
	public String findAllTeacher(String colId, String tbegin, HttpServletRequest request) {
		if(request.getSession().getAttribute("colleges") == null) {
			List<Colleges> colleges = colservice.findAllColl();
			request.getSession().setAttribute("colleges", colleges);
		}
		if (colId != null || tbegin != null) {
			int collId = Integer.valueOf(colId);
			int count = tservice.findTeachers(collId).size();
			int result[] = new PagesUtils(count, tbegin).getResult();
			List<Teachers> teachers = tservice.findTeacherByPage((result[2] - 1) * result[0], collId);
			
			request.getSession().setAttribute("ttotalPage", result[1]);
			request.getSession().setAttribute("colId", collId);
			request.getSession().setAttribute("tbegin", result[2]);
			request.getSession().setAttribute("teachers", teachers);
		}
		return head+"/manage_teachers.jsp";
	}

	// 添加或修改教师信息
	@RequestMapping("/addOrupdateTeacher.do")
	public String addOrupdateTeacher(String type, String tid, String tname, String tgender, String tnumber, 
			String tphone, String college, Model model) {
		int id = (tid == null || tid.equals("")) ? -1 : Integer.valueOf(tid);
		int err = exitMsg(id, tname, tnumber, tphone, college, model);
		if(err == 0) {
			int colId = colservice.exitCollege(college);
			String gender = (tgender.equals("女")) ? "F" : "M";
			Teachers teacher = new Teachers(id,tname,gender,tnumber,tphone,colId);
			tservice.addOrupdateTeacher(teacher);
			uservice.addOrUpdateUser(new User(tnumber,tnumber,1));
		} else {
			model.addAttribute("tname", tname);
			model.addAttribute("tgender", tgender);
			model.addAttribute("tnumber", tnumber);
			model.addAttribute("tphone", tphone);
			model.addAttribute("college", college);
		}// 根据type值判断返回哪个页面
		String addre = (id > 0) ? "redirect:findAllTeacher.do" : head+"/add_teacher.jsp";
		if(type.equals("all")) {
			addre = "redirect:findAllTeacher.do";
		} else if(type.equals("add")) {
			addre = head+"/add_teacher.jsp";
		} else {
			addre = "redirect:findTeacher.do?tnumber="+tnumber;
		}
		return addre;
	}
	
	// 校验数据
	public int exitMsg(int id, String tname, String tnumber, String tphone, String college, Model model) {
		int err = 0;
		boolean boo1 = true;
		boolean boo2 = true;
		if(id > 0) {
			Teachers teacher = tservice.findByTid(id);
			boo1 = !teacher.getT_number().equals(tnumber); 	// 相同则是false
			boo2 = !teacher.getT_phone().equals(tphone); 	// 相同则是false
		}
		if(tname.equals("") || tname == null) {
			err++;
		} 
		if(boo1 && (tnumber.length() != 5 || tservice.findTeacherByNumber(tnumber) != null)) {
			err++;
			model.addAttribute("tnumberError", "教师工号错误或已存在");
		}
		if(boo2 && (!Pattern.matches("^1[3|4|5|7|8]{1}\\d{9}$", tphone) || tservice.countPhone(tphone) > 0)) {
			err++;
			model.addAttribute("tphoneError", "手机号错误或已存在 ");
		}
		if(colservice.exitCollege(college) < 1) {
			err++;
			model.addAttribute("collegeError", "学院输入错误");
		}
		return err;
	}
}
