package spring.serviceImpl;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.beans.User;
import spring.dao.IUserDao;
import spring.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource(name="IUserDao")
	private IUserDao dao;
	public void setDao(IUserDao dao) {
		this.dao = dao;
	}

	@Transactional
	public User checkUser(User user) {
		User u = dao.selectUser(user);
		if(u != null && decrypt(u.getPassword()).equals(user.getPassword())) {
			u.setPassword(user.getPassword());
			return u;
		}
		return null;
	}

	@Transactional
	public void changePwd(User user) {
		user.setPassword(encrypt(user.getPassword()));
		dao.updateUser(user);
	}

	@Override
	public void addOrUpdateUser(User user) {
		user.setPassword(encrypt(user.getPassword()));
		if(user.getId() != null) {
			dao.updateUser(user);
		} else {
			dao.insertUser(user);
		}
	}

	@Transactional
	public void removeUser(String number) {
		dao.deleteUserByNumber(number);
	}

	@Override
	public void setType(int type) {
		dao.updateType(type);
	}

	@Override
	public int countClock() {
		return dao.selectCountClock();
	}
	
	// º”√‹
	public String encrypt(String password) {
		String key = "QWEdsazxcvbnmR123qwertTYUIyuiop45lkjhgf6OPL789KJHGFDSAZXCVBNM";
		int keyLen = key.length();
		String after = "";
		Random random = new Random();
		for(int i = 0; i < password.length(); i++) {
			int ran = random.nextInt(keyLen);
			after = after + (char)((int)password.charAt(i) + 4) + (char)((int)key.charAt(ran) + 4);
		}
		return after;
	}
		
	// Ω‚√‹
	public String decrypt(String password) {
		String mima = "";
		for(int j=0; j<password.length(); j=j+2) {
			int jie = (int)password.charAt(j) - 4;
			mima = mima + (char)jie;
		}
		return mima;
	}
}
