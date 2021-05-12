package poly.pt15307.sof3011.service;

import java.util.List;

import poly.pt15307.sof3011.dao.UserDao;
import poly.pt15307.sof3011.model.User;

public class UserService {
	UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	public User getById(int id) {
		
		return userDao.getById(id);
	}
	
	public boolean insert(User user) {
		return userDao.insert(user);
	}
	
	public boolean update(User user) {
		return userDao.update(user);
	}
	
	public boolean delete(int id) {
		return userDao.delete(id);
	}
	
	public User login(String email, String password) {
		return userDao.login(email, password);
	}
}
