package spring.service;

import spring.beans.User;

public interface IUserService {
	User checkUser(User user);
	
	void changePwd(User user);

	void addOrUpdateUser(User user);
	
	public void removeUser(String number);

	void setType(int i);

	int countClock();
}
