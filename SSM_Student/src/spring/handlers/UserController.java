package spring.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.beans.User;
import spring.service.IStudentService;
import spring.service.ITeachersService;
import spring.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired(required=false)
	private IUserService service;
	@Autowired()
	private ITeachersService tservice;
	@Autowired()
	private IStudentService stuservice;
	public void setService(IUserService service) {
		this.service = service;
	}
	public void setTservice(ITeachersService tservice) {
		this.tservice = tservice;
	}
	public void setStuservice(IStudentService stuservice) {
		this.stuservice = stuservice;
	}

	private String head = "/pages/jsp";

	// �û���¼
	@RequestMapping("/login.do")
	public String login(String username,String password,HttpServletRequest request) {
		String address = null;
		User u = new User(username,password);
		User user = null;
		try {
			user = service.checkUser(u);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(user != null) {
			request.getSession().setAttribute("user", user);
			if(user.getType() == 2) {
				address = head+"/manage/manager.jsp";
			} else if(user.getType() < 1){
				request.getSession().setAttribute("person", stuservice.findStudentByNumber(username));
				address = head+"/student/student.jsp";
			} else {
				request.getSession().setAttribute("person", tservice.findTeacherByNumber(username));
				address = head+"/teacher/teacher.jsp";
			}
		} else {
			request.setAttribute("username", username);
			request.setAttribute("loginError", "�û����������������");
			address =  head+"/login.jsp";
		}
		return address;
	}
	
	// �޸�����
	@RequestMapping("/changePWD.do")
	public String changePWD(String username, String oldPwd,String newPwd1,String newPwd2,HttpServletRequest request) {
		if(username == null || oldPwd == null) {
			return head+"/changePwd.jsp";
		}
		User user = (User) request.getSession().getAttribute("user");
		if(username != null && checkMsg(user,username,oldPwd,newPwd1,newPwd2,request) == 0) {
			user.setUsername(username);
			user.setPassword(newPwd1);
			service.changePwd(user);
			request.getSession().removeAttribute("user");
			return head+"/login.jsp";
		} else {
			return head+"/changePwd.jsp";
		}
	}
	
	// ��������Ƿ����Ҫ��
	private int checkMsg(User user, String username, String oldPwd, String newPwd1, String newPwd2,HttpServletRequest request) {
		int err = 0;
		if(username.equals("")) {
			err++;
			request.setAttribute("usernameError", "�û�������Ϊ��");
		}
		if(!user.getPassword().equals(oldPwd)) {
			err++;
			request.setAttribute("pwdError1", "��������ȷ��ԭ����");
		}
		if(!newPwd1.equals(newPwd2)) {
			err++;
			request.setAttribute("pwdError2", "������������벻һ��");
		} else if(newPwd1.length() < 5 || newPwd1.length() > 12) {
			err++;
			request.setAttribute("pwdError2", "���볤��Ӧ��5-12λ");
		}         
		return err;
	}
	
	// �û��˳���¼
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		String address = head+"/login.jsp";
		request.getSession().invalidate();
		return address;
	}
	
	// ����û�������û���Ϣ
	@RequestMapping("/addOrupUser.do")
	public String addOrupUser(String id, String username, String password) {
		User user = null;
		if (service.checkUser(new User(username,password)) == null) {
			user = new User(username,password,2);
			service.addOrUpdateUser(user);
		}
		return head+"/manage/manager.jsp";
	}
	
}
