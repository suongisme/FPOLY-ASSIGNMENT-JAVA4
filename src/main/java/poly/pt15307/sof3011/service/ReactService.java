package poly.pt15307.sof3011.service;

import poly.pt15307.sof3011.dao.ReactDao;
import poly.pt15307.sof3011.model.User;
import poly.pt15307.sof3011.model.Video;

public class ReactService {

	private ReactDao reactDao;
	
	public ReactService() {
		reactDao = new ReactDao();
	}
	
	public boolean insert(User user, Video video, boolean state) {
		return reactDao.insert(user, video, state);
	}
	
	public boolean delete(User user, Video video, boolean state) {
		return reactDao.delete(user, video, state);
	}
	
	public boolean update(User user, Video video, boolean state) {
		return reactDao.update(user, video, state);
	}
}
