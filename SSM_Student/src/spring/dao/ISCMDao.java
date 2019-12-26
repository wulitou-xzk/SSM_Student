package spring.dao;

import org.springframework.stereotype.Repository;

import spring.beans.SCM;

@Repository("ISCMDao")
public interface ISCMDao {

	void insertSCM(SCM scm);

	void updateSCM(SCM scm);

	void deleteSCM(String number);

	SCM selectSCMByNumber(String number);

}
