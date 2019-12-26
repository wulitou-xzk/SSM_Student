package spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.beans.Teachers;
import spring.dao.ITeachersDao;
import spring.service.ITeachersService;

@Service("teacherService")
public class TeachersServiceImpl implements ITeachersService {
	
	@Resource(name="ITeachersDao")
	private ITeachersDao dao;
	public void setDao(ITeachersDao dao) {
		this.dao = dao;
	}

	@Transactional
	public Teachers findTeacherByNumber(String tnumber) {
		return dao.selectTeacherByNumber(tnumber);
	}

	@Transactional
	public void delTeacherById(int id) {
		dao.deleteTeacherById(id);
	}

	@Transactional
	public List<Teachers> findTeachers(int collId) {
		return dao.selectAllTeacher(collId);
	}

	@Transactional
	public void addOrupdateTeacher(Teachers teacher) {
		if(teacher.getId() > 0) {
			dao.updateTeacher(teacher);
		} else {
			dao.insertTeacher(teacher);
		}
	}

	@Override
	public int countPhone(String tphone) {
		return dao.countPhone(tphone);
	}

	@Override
	public Teachers findByTid(int id) {
		return dao.selectTeacherById(id);
	}

	@Override
	public List<Teachers> findTeacherByPage(int begin, int collId) {
		return dao.selectTeacherByPage(begin, collId);
	}

}
