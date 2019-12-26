package spring.service;

import java.util.List;

import spring.beans.Teach_course;

public interface ITCService {

	Teach_course findCourseByTidCid(int teachId, int courseId);

	void addOrupdateTC(Teach_course course);

	void delTCById(int id);

	List<Teach_course> findTCByTid(int teachId);

	void deleteTCByCid(int courseId);

	List<Teach_course> findAllTC();

	void updateTCNumber(Teach_course course);

	List<Teach_course> findCourseByTid(int tid);

	List<Teach_course> findTCByPage(int begin);

}
