package spring.beans;

public class SCM {
	private Integer id;			// 主键
	private String stuNumber;	// 学生学号
	private int colId;			// 学院ID
	private int majorId;		// 专业ID
	
	public SCM() {
		super();
	}
	
	public SCM(String stuNumber, int colId, int majorId) {
		super();
		this.stuNumber = stuNumber;
		this.colId = colId;
		this.majorId = majorId;
	}

	public SCM(Integer id, String stuNumber, int colId, int majorId) {
		super();
		this.id = id;
		this.stuNumber = stuNumber;
		this.colId = colId;
		this.majorId = majorId;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getColId() {
		return colId;
	}
	public void setColId(int colId) {
		this.colId = colId;
	}
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	@Override
	public String toString() {
		return "SCM [id=" + id + ", stuNumber=" + stuNumber + ", colId="
				+ colId + ", majorId=" + majorId + "]";
	}
	
}
