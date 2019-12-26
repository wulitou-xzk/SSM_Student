package spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.beans.Courses;
import spring.beans.Student_Course;
import spring.dao.ICoursesDao;
import spring.dao.ISCDao;
import spring.service.ISCService;

@Service("scService")
public class SCServiceImpl implements ISCService {
	
	@Resource(name="ISCDao")
	private ISCDao dao;
	@Resource(name="ICoursesDao")
	private ICoursesDao cdao;
	public void setCdao(ICoursesDao cdao) {
		this.cdao = cdao;
	}
	public void setDao(ISCDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void expelSC(Integer sid) {
		dao.deleteSC(sid);
	}

	// 根据id属性值判断进行添加操作还是更新操作
	@Transactional
	public void addOrupdateSC(int id, int sid, int cid, int tid, double score) {
		if(id <= 0) {
			dao.insertSC(sid,cid,tid,score);
		} else {
			dao.updateSC(id,sid,cid,tid,score);
		}
	}
	
	@Override
	public void deleteSCByCid(int cid) {
		dao.deleteSCByCid(cid);
	}
	
	@Transactional
	public void removeSCBySidCid(int sid,int cid) {
		dao.deleteSCBySidCid(sid,cid);
	}
	
	@Transactional
	public Courses findCourseByName(String cname) {
		return cdao.selectCourseByName(cname);
	}
	
	@Transactional
	public void addOrupdateCourse(Courses courses) {
		if(courses.getId() == 0) {
			cdao.addCourse(courses);
		} else {
			cdao.updateCourse(courses);
		}
	}
	
	@Transactional
	public void deleteCourseById(int id) {
		cdao.deleteCourseById(id);
	}
	
	@Transactional
	public List<Courses> findAllCourses() {
		return cdao.findAllCourse();
	}

	@Transactional
	public List<Student_Course> findRevisedCourses(int sid) {
		return dao.selectRevisedCourses(sid);
	}
	@Override
	public Student_Course getSCBySidCid(int sid, int cid) {
		return dao.selectBySidCid(sid,cid);
	}
	@Override
	public Courses findCourseById(int id) {
		return cdao.selectCourseById(id);
	}
	@Override
	public int getRevised(int sid) {
		return dao.countRevised(sid);
	}
	@Override
	public void onOroffLock(int cid, int lock) {
		cdao.updateLock(cid,lock);
	}
	@Override
	public void onOrOffAllLock(int lock) {
		cdao.updateAllLock(lock);
	}
	@Override
	public List<Courses> findCourseByCoId(int coId) {
		return cdao.selectByCoId(coId);
	}
	@Override
	public int CountSize() {
		return cdao.Count();
	}
	@Override
	public List<Courses> finCourseByPage(int begin) {
		return cdao.selectCourseByPage(begin);
	}
}
