package spring.beans;

public class Teachers {
	
	private Integer id;			// 教师Id
	private String t_name;		// 教师姓名
	private String t_gender;	// 教师性别
	private String t_number;	// 教师工号
	private String t_phone;		// 教师联系方式
	private int collId;			// 教师所在学院Id
	
	private String college;		// 教师所在学院
	
	public Teachers() {
		super();
	}
	
	public Teachers(Integer id, String t_name, String t_gender,
			String t_number, String t_phone, int collId) {
		super();
		this.id = id;
		this.t_name = t_name;
		this.t_gender = t_gender;
		this.t_number = t_number;
		this.t_phone = t_phone;
		this.collId = collId;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_gender() {
		return t_gender;
	}
	public void setT_gender(String t_gender) {
		this.t_gender = t_gender;
	}
	public String getT_number() {
		return t_number;
	}
	public void setT_number(String t_number) {
		this.t_number = t_number;
	}
	public String getT_phone() {
		return t_phone;
	}
	public void setT_phone(String t_phone) {
		this.t_phone = t_phone;
	}
	public int getCollId() {
		return collId;
	}
	public void setCollId(int collId) {
		this.collId = collId;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	@Override
	public String toString() {
		return "Teachers [id=" + id + ", t_name=" + t_name + ", t_gender="
				+ t_gender + ", t_number=" + t_number + ", t_phone=" + t_phone
				+ ", collId=" + collId + ", college=" + college + "]";
	}
	
}
