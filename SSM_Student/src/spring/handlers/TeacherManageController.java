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
	
	/*************************************��ʦ�ڿ���Ϣ******************************************/
	
	// ȡ���ڿ�
	@RequestMapping("/delTC.do")
	public String removeTCById(String id, String tid, String tname) {
		tcservice.delTCById(Integer.valueOf(id));
		return "redirect:findTC.do?tid="+tid+"&tname="+tname;
	}
	
	// ��ӻ�����ڿ�
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
			Teach_course tc = new Teach_course(Id, teachId, courseId, num, max, place, turnWeek(0, day), on+"-"+off, begin+"-"+end+"��");
			tcservice.addOrupdateTC(tc);
		}
		return "redirect:findTC.do?tid="+tid+"&tname="+tname;
	}
	
	// �����ڿ���Ϣ
	@RequestMapping("/findTC.do")
	public String findTCByTid(String type, String tid, String tname, HttpServletRequest request) {
		int teachId = Integer.valueOf(tid);
		List<Teach_course> tc = tcservice.findTCByTid(teachId);
		if(tc.size() > 0) {
			for (Teach_course t : tc) {
				int num[] = turnOnOff(t.getTime(), 1);
				String week = t.getWeek();
				t.setBegin(week.substring(0,week.indexOf("-")));
				t.setEnd(week.substring(week.indexOf("-")+1,week.indexOf("��")));
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
	// ת����ʽ
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
	
	// ת����Ӣ��
	public String turnWeek(int type, String day) {
		String chi[] = {"����һ","���ڶ�","������","������","������","������","������"};
		String eng[] = {"Mon","Tues","Wednes","Thurs","Fri","Satur","Sun"};
		if(type == 0) {  // ��->Ӣ
			return eng["һ������������".indexOf(day.charAt(2))];
		} else {  // Ӣ->��
			return chi["MoTuWeThFrSaSu".indexOf(day.substring(0, 2)) / 2];
		}
	}
	
	// ��ϢУ��
	public int exitMsgTC(String cname, String maxnumber, String begin, String end, String on, String off, HttpServletRequest request) {
		int err = 0;
		if(cname == null || cname.equals("") || scservice.findCourseByName(cname).getId() < 1) {
			err++;
			request.setAttribute("cnameError", "�γ�������");
		}
		if(!Pattern.matches("^[0-9]{1,}$", maxnumber)) {
			err++;
			request.setAttribute("numError", "��������");
		}
		if(Integer.valueOf(begin) < 1 || Integer.valueOf(begin) > 15) {
			err++;
			request.setAttribute("weekError", "����");
		}
		if(Integer.valueOf(end) < Integer.valueOf(begin) || Integer.valueOf(end) > 16) {
			err++;
			request.setAttribute("weekError", "����");
		}
		if(turnOnOff(on+"-"+off, 0)[0] == -1) {
			err++;
			request.setAttribute("timeError", "�������");
		}
		return err;
	}
	
	/*************************************��ʦ������Ϣ******************************************/
	
	// ���ݽ�ʦ���Ų�ѯ��ʦ��Ϣ
	@RequestMapping("/findTeacher.do")
	public String findTeacherByNumber(String tnumber, Model model) {
		if(tnumber == null || tnumber.equals("") || tnumber.length() > 5) {
			model.addAttribute("tnumberError", "��������ȷ��ʦ����");
			return head+"/manage_teachers.jsp";
		} else {
			Teachers teacher = tservice.findTeacherByNumber(tnumber);
			if(teacher == null) {
				model.addAttribute("tnumberError", "���ҽ�ʦʧ�ܣ���ȷ�Ϲ����Ƿ�����");
				return head+"/manage_teachers.jsp";
			}
			String gender = teacher.getT_gender().equals("M") ? "��" : "Ů";
			teacher.setT_gender(gender);
			model.addAttribute("teacher", teacher);
		}
		return head+"/manage_oneteacher.jsp";
	}

	// ���ݽ�ʦidɾ����ʦ
	@RequestMapping("/delTeacher.do")
	public String removeTeacherById(String tid) {
		tservice.delTeacherById(Integer.valueOf(tid));
		return "redirect:findAllTeacher.do";
	}
	
	// ��ȡ���н�ʦ����Ϣ
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

	// ��ӻ��޸Ľ�ʦ��Ϣ
	@RequestMapping("/addOrupdateTeacher.do")
	public String addOrupdateTeacher(String type, String tid, String tname, String tgender, String tnumber, 
			String tphone, String college, Model model) {
		int id = (tid == null || tid.equals("")) ? -1 : Integer.valueOf(tid);
		int err = exitMsg(id, tname, tnumber, tphone, college, model);
		if(err == 0) {
			int colId = colservice.exitCollege(college);
			String gender = (tgender.equals("Ů")) ? "F" : "M";
			Teachers teacher = new Teachers(id,tname,gender,tnumber,tphone,colId);
			tservice.addOrupdateTeacher(teacher);
			uservice.addOrUpdateUser(new User(tnumber,tnumber,1));
		} else {
			model.addAttribute("tname", tname);
			model.addAttribute("tgender", tgender);
			model.addAttribute("tnumber", tnumber);
			model.addAttribute("tphone", tphone);
			model.addAttribute("college", college);
		}// ����typeֵ�жϷ����ĸ�ҳ��
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
	
	// У������
	public int exitMsg(int id, String tname, String tnumber, String tphone, String college, Model model) {
		int err = 0;
		boolean boo1 = true;
		boolean boo2 = true;
		if(id > 0) {
			Teachers teacher = tservice.findByTid(id);
			boo1 = !teacher.getT_number().equals(tnumber); 	// ��ͬ����false
			boo2 = !teacher.getT_phone().equals(tphone); 	// ��ͬ����false
		}
		if(tname.equals("") || tname == null) {
			err++;
		} 
		if(boo1 && (tnumber.length() != 5 || tservice.findTeacherByNumber(tnumber) != null)) {
			err++;
			model.addAttribute("tnumberError", "��ʦ���Ŵ�����Ѵ���");
		}
		if(boo2 && (!Pattern.matches("^1[3|4|5|7|8]{1}\\d{9}$", tphone) || tservice.countPhone(tphone) > 0)) {
			err++;
			model.addAttribute("tphoneError", "�ֻ��Ŵ�����Ѵ��� ");
		}
		if(colservice.exitCollege(college) < 1) {
			err++;
			model.addAttribute("collegeError", "ѧԺ�������");
		}
		return err;
	}
}
