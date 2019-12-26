package spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import spring.beans.Student;

@Repository("IStudentDao")
public interface IStudentDao {

	void insertStudent(Student student);

	Student check(Student student);

	void updateStudent(Student student);

	void deleteStudentByNumber(@Param("number")String number);

	Student selectStudentByNumber(@Param("number")String number);

	List<Student> selectAll();

	List<Student> selectStudentByTidCid(@Param("teachId")int teachId, @Param("courseId")int courseId);

	List<Student> selectByPage(@Param("colId")int colId, @Param("begin")int begin);

	int count(@Param("colId")int colId);

	List<Student> selectStudentByPage(int teachId, int courseId, int begin);
	
}
