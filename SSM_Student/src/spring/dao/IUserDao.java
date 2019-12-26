package spring.dao;

import org.springframework.stereotype.Repository;

import spring.beans.User;

@Repository("IUserDao")
public interface IUserDao {

	User selectUser(User user);
	
	void insertUser(User user);

	void updateUser(User user);

	void deleteUserByNumber(String username);

	void updateType(int type);

	int selectCountClock();

}
