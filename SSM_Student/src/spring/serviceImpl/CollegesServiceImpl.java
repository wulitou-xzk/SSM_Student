package spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.beans.Colleges;
import spring.dao.ICollegesDao;
import spring.service.ICollegesService;

@Service("collegesService")
public class CollegesServiceImpl implements ICollegesService {
	
	@Resource(name="ICollegesDao")
	private ICollegesDao dao;
	public void setDao(ICollegesDao dao) {
		this.dao = dao;
	}
	public ICollegesDao getDao() {
		return dao;
	}


	@Transactional
	public int exitCollege(String college) {
		if(dao.selectCollegeByName(college) != null)
			return dao.selectCollegeByName(college).getId();
		return -1;
	}
	@Override
	public Colleges findCollById(int colId) {
		return dao.selectCollegeById(colId);
	}
	@Override
	public Colleges findCollByName(String college) {
		return dao.selectCollegeByName(college);
	}
	@Override
	public List<Colleges> findAllColl() {
		return dao.selectAllColleges();
	}

}
