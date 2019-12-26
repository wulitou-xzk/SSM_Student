package spring.service;

import java.util.List;

import spring.beans.Teachers;

public interface ITeachersService {

	Teachers findTeacherByNumber(String tnumber);

	void delTeacherById(int id);

	List<Teachers> findTeachers(int collId);

	void addOrupdateTeacher(Teachers teacher);

	int countPhone(String tphone);

	Teachers findByTid(int id);

	List<Teachers> findTeacherByPage(int begin, int collId);

}
