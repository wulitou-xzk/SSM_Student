package spring.beans;

public class Teach_course {
	
	private Integer id;			// ����
	private int teachId;		// �ڿν�ʦID
	private int courseId;		// �γ�ID
	private int number;			// ��ѡ������
	private int maxnumber;		// ��������
	private String place;		// �Ͽεص�
	private String day;			// �ܼ��Ͽ�
	private String time;		// �Ͽν���
	private String week;		// �Ͽ�������
	
	private int sid;			// ѧ��id
	private String t_name;		// �ڿν�ʦ����
	private String t_number; 	// ��ʦ����
	private String cname;		// �γ�����
	private String college;		// ����ѧԺ
	private String begin;		// �γ̿�ʼ��
	private String end;			// �γ̽�����
	private String on;			
	private String off;
	
	public Teach_course() {
		super();
	}
	
	public Teach_course(int teachId, int courseId) {
		super();
		this.teachId = teachId;
		this.courseId = courseId;
	}

	public Teach_course(Integer id, int teachId, int courseId, int number,
			int maxnumber, String place, String day, String time, String week) {
		super();
		this.id = id;
		this.teachId = teachId;
		this.courseId = courseId;
		this.number = number;
		this.maxnumber = maxnumber;
		this.place = place;
		this.day = day;
		this.time = time;
		this.week = week;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getTeachId() {
		return teachId;
	}

	public void setTeachId(int teachId) {
		this.teachId = teachId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getMaxnumber() {
		return maxnumber;
	}

	public void setMaxnumber(int maxnumber) {
		this.maxnumber = maxnumber;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getOn() {
		return on;
	}

	public void setOn(String on) {
		this.on = on;
	}

	public String getOff() {
		return off;
	}

	public void setOff(String off) {
		this.off = off;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
 
	public String getT_number() {
		return t_number;
	}

	public void setT_number(String t_number) {
		this.t_number = t_number;
	}

	@Override
	public String toString() {
		return "Teach_course [id=" + id + ", teachId=" + teachId
				+ ", courseId=" + courseId + ", number=" + number
				+ ", maxnumber=" + maxnumber + ", place=" + place + ", day="
				+ day + ", time=" + time + ", week=" + week + ", sid=" + sid
				+ ", t_name=" + t_name + ", t_number=" + t_number + ", cname="
				+ cname + ", college=" + college + ", begin=" + begin
				+ ", end=" + end + ", on=" + on + ", off=" + off + "]";
	}
	
	

}
