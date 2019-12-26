package spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import spring.beans.Teach_course;

@Repository("ITCDao")
public interface ITCDao {

	void insertTC(Teach_course course);

	void deleteTCById(@Param("id")int id);

	void updateTC(Teach_course course);

	void deleteTCByCid(@Param("courseId")int courseId);

	List<Teach_course> selectTCByTid(@Param("teachId")int teachId);
	
	Teach_course selectTCByTidCid(@Param("teachId")int teachId, @Param("courseId")int courseId);

	List<Teach_course> selectAllTC();

	void updateNumberByTidCid(Teach_course course);

	List<Teach_course> selectCourseByTid(@Param("teachId")int teachId);

	List<Teach_course> selectTCByPage(int begin);

}
