package spring.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

import spring.beans.Colleges;
import spring.beans.Student;
import spring.dao.ICollegesDao;
import spring.handlers.StudentManagerController;
import spring.serviceImpl.SCServiceImpl;
import spring.serviceImpl.StudentServiceImpl;

public class MyTest {
	
	@Resource(name="ICollegesDao")
	private ICollegesDao collegeDao;
	public void setCollegeDao(ICollegesDao collegeDao) {
		this.collegeDao = collegeDao;
	}

	@Test
	public void test01() {
		
//		String str = "2012-12-11";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = null;
//		try {
//			date = sdf.parse(str);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		System.out.println(date);
		
		List<Colleges> list = collegeDao.selectAllColleges();
		for (Colleges colleges : list) {
			System.out.println(colleges);
		}
	}
	
	@Test
	public void test02() {
		String number = "1202020202";
		StudentServiceImpl service = new StudentServiceImpl();
		Student student = new Student("肖志奎",number,"男",new Date(), "2016");
		//int a = service.checkStudent(student);
		service.addStudent(student);
//		for (Student student2 : allStudent) {
//			System.out.println(student2);
//		}
	}
	
	@Test
	public void test03() {
		System.out.println(new Date());
		System.out.println(String.format("%tY-%<tm-%<td", new Date()));
	}
	@Test
	public void test04() {
		String week = "10-8周";
		String begin = week.substring(0,week.indexOf("-"));
		String end = week.substring(week.indexOf("-")+1,week.indexOf("周"));
		System.out.println(begin+"\n"+end);
	}
	@Test
	public void test05() {
		String day = "星期一";
		String day2 = "Sun";
		String chi[] = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
		String eng[] = {"Mon","Tues","Wednes","Thurs","Fri","Satur","Sun"};
		System.out.println(eng["一二三四五六七".indexOf(day.charAt(2))]);
		System.out.println(chi["MoTuWeThFrSaSu".indexOf(day2.substring(0, 2)) / 2]);
	}
	@Test
	public void test07() {
		String str = "QWEdsazxcvbnmR123qwertTYUIyuiop45lkjhgf6OPL789KJHGFDSAZXCVBNM";
		System.out.println((char)(1111));
	}
	
	@Test
	public void test06() {
		String stupwd = "1609103024";
		String password[] = {"test00"};
		String after[] = jia(password);
		String answer[] = jie(new String[]{"xOiHwjxZ4H4y"});
		System.out.println("密码加密后：");
		for (String af : after) {
			System.out.println(af);
		}
		System.out.println("解密后：");
		for (String av : answer) {
			System.out.println(av);
		}
	}
	
	@Test
	public void insertUser() {
		String username = "16091020";
		String user[] = new String[44];
		for(int i = 1; i < 45; i++) {
			if(i < 10) {
				user[i-1] = username + "0" + i;
			} else {
				user[i-1] = username + i;
			}
		}
		String jiami[] = jia(user);
		String jiemi[] = jie(jiami);
		for(int i = 0; i < 44; i++) {
			System.out.println(user[i] + " : " + jiami[i] + " -- " + jiemi[i]);
		}
		for (String string : jiami) {
			if(string.contains("\'"))
				System.out.println("---");
		}
	}
	
	public String[] jia(String pwd[]) {
		String str = "QWEdsazxcvbnmR123qwertTYUIyuiop45lkjhgf6OPL789KJHGFDSAZXCVBNM";
		int len = str.length();
		String after[] = new String[pwd.length];
		Random random = new Random();
		for(int j=0; j<pwd.length; j++) {
			String before = pwd[j];
			String middle = "";
			for(int i=0; i<pwd[j].length(); i++) {
				int r = random.nextInt(len);
				middle = middle + (char)(before.charAt(i) + 4) + (char)(str.charAt(r) + 4);
			}
			after[j] = middle;
		}
		return after;
	}
	
	public String[] jie(String pwd[]) {
		String ma[] = new String[pwd.length];
		for(int i=0; i<pwd.length; i++) {
			String before = pwd[i];
			String after = "";
			for(int j=0; j<before.length(); j=j+2) {
				int jie = before.charAt(j) - 4;
				after = after + (char)jie;
			}
			ma[i] = after;
		}
		return ma;
	}
	
	@Test
	public void tesse() {
		String pwd = "test00";
		String encr = encrypt(pwd);
		System.out.println(encr);
		String de = decrypt(encr);
		System.out.println("\n"+de);
	}
	
	// 加密
	public String encrypt(String password) {
		String key = "QWEdsazxcvbnmR123qwertTYUIyuiop45lkjhgf6OPL789KJHGFDSAZXCVBNM";
		int keyLen = key.length();
		String after = "";
		Random random = new Random();
		for(int i = 0; i < password.length(); i++) {
			int ran = random.nextInt(keyLen);
			after = after + (char)((int)password.charAt(i) + 4) + (char)((int)key.charAt(ran) + 4);
		}
		return after;
	}
		
	// 解密
	public String decrypt(String password) {
		String mima = "";
		for(int j=0; j<password.length(); j = j + 2) {
			int jie = (int)password.charAt(j) - 4;
			mima = mima + (char)jie;
		}
		return mima;
	}
	
//	public String encrypt (String sourceString, String password) {
//		char p[]=password.toCharArray();
//		int lenp=p.length;
//		char sou[]=sourceString.toCharArray();
//		int lens=sou.length;
//		for(int i=0; i<lens; i++) {
//			int mima=sou[i]+p[i%lenp]; //加密过程
//			sou[i]=(char)mima;
//		}
//		return new String(sou); //返回已加密密文
// 	}
//	public String decrypt (String sourceString, String password) {
// 		char p[]=password.toCharArray();
//		int lenp=p.length;
//		char sou[]=sourceString.toCharArray();
//		int lens=sou.length;
//		for(int i=0; i<lens; i++) {
//			int mima=sou[i]-p[i%lenp]; //解密过程
//			sou[i]=(char)mima;
//		}
//		return new String(sou); //返回明文
// 	}
}

