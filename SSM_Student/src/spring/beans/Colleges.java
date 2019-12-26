package spring.beans;

public class Colleges {
	private Integer id;
	private String college; // Ñ§ÔºÃû³Æ
	
	public Colleges(Integer id, String college) {
		super();
		this.id = id;
		this.college = college;
	}
	public Colleges() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	@Override
	public String toString() {
		return "Colleges [id=" + id + ", college=" + college + "]";
	}
}
