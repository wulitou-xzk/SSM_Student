package spring.service;

import java.util.List;

import spring.beans.Courses;
import spring.beans.Student_Course;

public interface ISCService {

	void expelSC(Integer sid);

	void addOrupdateSC(int id, int sid, int cid, int tid, double score);

	void removeSCBySidCid(int sid, int cid);

	void deleteSCByCid(int cid);
	
	List<Student_Course> findRevisedCourses(int sid);
	
	Student_Course getSCBySidCid(int sid, int cid);
	
	int getRevised(int sid);
	
	void addOrupdateCourse(Courses courses);

	void deleteCourseById(int id);

	List<Courses> findAllCourses();

	Courses findCourseByName(String cname);
	
	Courses findCourseById(int id);

	void onOroffLock(int cid, int lock);

	void onOrOffAllLock(int lock);

	List<Courses> findCourseByCoId(int coId);

	int CountSize();

	List<Courses> finCourseByPage(int begin);
}
