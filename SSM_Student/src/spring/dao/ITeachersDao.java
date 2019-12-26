package spring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.beans.Teachers;

public interface ITeachersDao {

	Teachers selectTeacherByNumber(@Param("t_number")String t_number);

	void deleteTeacherById(@Param("id")int id);

	List<Teachers> selectAllTeacher(@Param("collId")int collId);

	void updateTeacher(Teachers teacher);

	void insertTeacher(Teachers teacher);

	int countPhone(@Param("t_phone")String t_phone);

	Teachers selectTeacherById(int id);

	List<Teachers> selectTeacherByPage(@Param("begin")int begin, @Param("collId")int collId);

}
