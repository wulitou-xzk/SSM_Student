package spring.beans;

import java.util.Date;

public class Student {
	
	private Integer id;     //��Ա������˽��
	private String sname;   // ����
	private String number;  // ѧ��
	private String gender;  // �Ա�
	private Date birth;     // ��������
	private String grader;  // �꼶
	private String college; // ����ѧԺ
	private String major;   // ��ѧרҵ
	
	private double score;	// ѧ���ɼ�
	
	/*select s.sname,s.number,s.gender,s.grader,sc.score,co.college,m.major 
	 * from student s,stu_course sc,colleges co,majors m,teach_course tc,stu_col_maj scm 
	 * where s.id=sc.sid and sc.cid=tc.courseId and sc.tid=tc.teachId and scm.stuNumber=s.number and scm.colId=co.id and scm.majorId=m.id and tc.teachId=#{teachId} and tc.courseId=#{courseId};*/
	
	public Student() {
		super();
	}

	public Student(String sname, String number, String gender, Date birth,
			String grader) {
		super();
		this.sname = sname;
		this.number = number;
		this.gender = gender;
		this.birth = birth;
		this.grader = grader;
	}

	public Student(String sname, String number, String gender, Date birth,
			String grader, String college, String major) {
		super();
		this.sname = sname;
		this.number = number;
		this.gender = gender;
		this.birth = birth;
		this.grader = grader;
		this.college = college;
		this.major = major;
	}

	public Student(Integer id, String sname, String number, String gender,
			Date birth, String grader) {
		super();
		this.id = id;
		this.sname = sname;
		this.number = number;
		this.gender = gender;
		this.birth = birth;
		this.grader = grader;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public String getGrader() {
		return grader;
	}
	public void setGrader(String grader) {
		this.grader = grader;
	}
	
	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", sname=" + sname + ", number=" + number
				+ ", gender=" + gender + ", birth=" + birth + ", grader="
				+ grader + ", college=" + college + ", major=" + major
				+ ", score=" + score + "]";
	}
}
