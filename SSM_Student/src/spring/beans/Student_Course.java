package spring.beans;

public class Student_Course {
	
	private Integer id;			// ID
	private int sid;			// 学生id
	private int cid;			// 课程id
	private int tid;			// 授课教师id
	private double score;		// 课程成绩
	
	private String cname;		// 课程名称
	private String t_name;		// 授课教师
	private String college;		// 开课学院
	
	public Student_Course() {
		super();
	}
	
	public Student_Course(Integer id, int sid, int cid, double score) {
		super();
		this.id = id;
		this.sid = sid;
		this.cid = cid;
		this.score = score;
	}

	public Student_Course(int id, int sid, int cid, int tid, double score) {
		super();
		this.id = id;
		this.sid = sid;
		this.cid = cid;
		this.tid = tid;
		this.score = score;
	}
	
	public Student_Course(Integer id, int sid, int cid, int tid, double score,
			String cname, String t_name, String college) {
		super();
		this.id = id;
		this.sid = sid;
		this.cid = cid;
		this.tid = tid;
		this.score = score;
		this.cname = cname;
		this.t_name = t_name;
		this.college = college;
	}
	public Student_Course(int sid, int cid, double score, String cname,
			String t_name) {
		super();
		this.sid = sid;
		this.cid = cid;
		this.score = score;
		this.cname = cname;
		this.t_name = t_name;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "Student_Course [id=" + id + ", sid=" + sid + ", cid=" + cid
				+ ", tid=" + tid + ", score=" + score + ", cname=" + cname
				+ ", t_name=" + t_name + ", college=" + college + "]";
	}
}
