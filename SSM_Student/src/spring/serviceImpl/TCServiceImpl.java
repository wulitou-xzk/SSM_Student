package spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.beans.Teach_course;
import spring.dao.ITCDao;
import spring.service.ITCService;

@Service("tcService")
public class TCServiceImpl implements ITCService {
	
	@Resource(name="ITCDao")
	private ITCDao dao;
	public void setDao(ITCDao dao) {
		this.dao = dao;
	}

	@Transactional
	public Teach_course findCourseByTidCid(int teachId, int courseId) {
		return dao.selectTCByTidCid(teachId,courseId);
	}

	@Transactional
	public void addOrupdateTC(Teach_course course) {
		if(course.getId() == 0) {
			dao.insertTC(course);
		} else {
			dao.updateTC(course);
		}
	}

	@Transactional
	public void delTCById(int id) {
		dao.deleteTCById(id);
	}

	@Transactional
	public List<Teach_course> findTCByTid(int teachId) {
		return dao.selectTCByTid(teachId);
	}

	@Transactional
	public void deleteTCByCid(int courseId) {
		dao.deleteTCByCid(courseId);
	}

	@Override
	public List<Teach_course> findAllTC() {
		return dao.selectAllTC();
	}

	@Override
	public void updateTCNumber(Teach_course course) {
		dao.updateNumberByTidCid(course);
	}

	@Override
	public List<Teach_course> findCourseByTid(int tid) {
		return dao.selectCourseByTid(tid);
	}

	@Override
	public List<Teach_course> findTCByPage(int begin) {
		return dao.selectTCByPage(begin);
	}

}
