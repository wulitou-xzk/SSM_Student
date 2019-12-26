package spring.beans;

public class Majors {
	private Integer id;
	private String major;   // 专业名称 
	private int collegeId;  // 所属学院的id
	
	public Majors() {
		super();
	}

	public Majors(Integer id, String major, int collegeId) {
		super();
		this.id = id;
		this.major = major;
		this.collegeId = collegeId;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}
	@Override
	public String toString() {
		return "Majors [id=" + id + ", major=" + major + ", collegeId="
				+ collegeId + "]";
	}
}
