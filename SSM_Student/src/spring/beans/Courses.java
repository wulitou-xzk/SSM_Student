package spring.beans;

public class Courses {
	
	private Integer id;
	private String cname; 		// 课程名称
	private int clock;  		// 课程锁   0/1  开启/关闭选课
	private int coId;			// 授课学院Id
	private String college;		// 开课学院
	
	public Courses() {
		super();
	}
	public Courses(Integer id, String cname, int clock, int coId) {
	super();
	this.id = id;
	this.cname = cname;
	this.clock = clock;
	this.coId = coId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getClock() {
		return clock;
	}
	public void setClock(int clock) {
		this.clock = clock;
	}
	public int getCoId() {
		return coId;
	}
	public void setCoId(int coId) {
		this.coId = coId;
	}
	
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	@Override
	public String toString() {
		return "Courses [id=" + id + ", cname=" + cname + ", clock=" + clock
				+ ", coId=" + coId + ", college=" + college + "]";
	}
}
