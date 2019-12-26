package spring.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.beans.Majors;
import spring.dao.IMajorsDao;
import spring.service.IMajorsService;

@Service("majorsService")
public class MajorsServiceImpl implements IMajorsService {
	
	@Resource(name="IMajorsDao")
	private IMajorsDao dao;
	public void setDao(IMajorsDao dao) {
		this.dao = dao;
	}

	@Transactional
	public Majors exitMajor(String major) {
		return dao.selectMajorIdByName(major);
	}
	@Override
	public Majors findMajById(int majorId) {
		return dao.selectMajorById(majorId);
	}
	@Override
	public List<Majors> findAllMajor() {
		return dao.selectAllMajors();
	}
	@Override
	public List<Majors> findByColId(Integer collegeId) {
		return dao.selectMajorByColId(collegeId);
	}

}
