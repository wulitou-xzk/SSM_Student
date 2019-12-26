package spring.service;

import java.util.List;

import spring.beans.Student;

public interface IStudentService {

	void addStudent(Student student);

	boolean checkStudent(Student student);
	
	void updateStudent(Student student);

	void expelStudent(String number);

	Student findStudentByNumber(String number);

	List<Student> findAll();

	List<Student> findStudentByTidCid(int teachId, int courseId, int begin);

	List<Student> findByPage(int colId, int begin);

	int CountSize(int colId);
	
}
