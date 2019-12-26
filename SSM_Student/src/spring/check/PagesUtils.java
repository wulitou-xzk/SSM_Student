package spring.check;

public class PagesUtils {
	
	private int pageSize = 5;
	
	private int count;
	private String begin;
	
	private int newBegin;
	private int totalPage;
	
	public PagesUtils() {
	}
	
	public PagesUtils(int count, String begin) {
		super();
		this.count = count;
		this.begin = begin;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}
	
	public int[] getResult() {
		totalPage = (count % pageSize == 0) ? count / pageSize : count / pageSize + 1;
		newBegin = (begin == null || begin.equals("")) ? 1 : Integer.valueOf(begin);
		return new int[] {pageSize,totalPage,newBegin};
	}
}
