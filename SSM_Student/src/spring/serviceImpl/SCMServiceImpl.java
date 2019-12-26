package spring.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.beans.SCM;
import spring.dao.ISCMDao;
import spring.service.ISCMService;

@Service("scmService")
public class SCMServiceImpl implements ISCMService {
	
	@Resource(name="ISCMDao")
	private ISCMDao dao;
	
	public void setDao(ISCMDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void addSCM(SCM scm) {
		dao.insertSCM(scm);
	}
	@Override
	public void updateSCM(SCM scm) {
		dao.updateSCM(scm);
	}
	@Override
	public void expelSCM(String number) {
		dao.deleteSCM(number);
		
	}
	@Override
	public SCM findSCMByNumber(String number) {
		return dao.selectSCMByNumber(number);
	}

}
