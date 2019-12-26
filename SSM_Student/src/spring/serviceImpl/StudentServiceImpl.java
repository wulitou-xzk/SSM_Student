package spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.beans.Student;
import spring.dao.IStudentDao;
import spring.service.IStudentService;

@Service("studentService")
public class StudentServiceImpl implements IStudentService {

	@Resource(name="IStudentDao")
	private IStudentDao dao;
	public void setDao(IStudentDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void addStudent(Student student) {
		dao.insertStudent(student);
	}

	@Transactional
	public boolean checkStudent(Student student) {
		return dao.check(student) != null;
	}

	@Transactional
	public void expelStudent(String number) {
		dao.deleteStudentByNumber(number);
	}
	@Override
	public void updateStudent(Student student) {
		dao.updateStudent(student);
	}
	@Override
	public Student findStudentByNumber(String number) {
		return dao.selectStudentByNumber(number);
	}
	@Override
	public List<Student> findAll() {
		return dao.selectAll();
	}

	@Override
	public List<Student> findStudentByTidCid(int teachId, int courseId, int begin) {
		if(begin == 0)
			return dao.selectStudentByTidCid(teachId, courseId);
		else
			return dao.selectStudentByPage(teachId, courseId, begin);
	}

	@Override
	public List<Student> findByPage(int colId, int begin) {
		return dao.selectByPage(colId, begin);
	}

	@Override
	public int CountSize(int colId) {
		return dao.count(colId);
	}

}
