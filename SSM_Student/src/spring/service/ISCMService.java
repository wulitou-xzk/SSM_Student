package spring.service;

import spring.beans.SCM;

public interface ISCMService {

	void addSCM(SCM scm);

	void updateSCM(SCM scm);

	void expelSCM(String number);

	SCM findSCMByNumber(String number);

}
