package spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import spring.beans.Courses;

@Repository("ICoursesDao")
public interface ICoursesDao {
	
	Courses selectCourseByName(@Param("cname")String cname);
	
	List<Courses> findAllCourse();
	
	void addCourse(Courses courses);

	void updateCourse(Courses courses);

	void deleteCourseById(@Param("id")int id);
	
	Courses selectCourseById(@Param("id")int id);

	void updateLock(@Param("id")int id, @Param("clock")int clock);

	void updateAllLock(@Param("clock")int clock);

	List<Courses> selectByCoId(@Param("coId")int coId);

	int Count();

	List<Courses> selectCourseByPage(int begin);

}
