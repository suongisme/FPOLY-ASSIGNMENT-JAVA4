package poly.pt15307.sof3011.service;

import java.util.ArrayList;
import java.util.List;

import poly.pt15307.sof3011.dao.VideoDao;
import poly.pt15307.sof3011.model.Video;

public class VideoService {
	VideoDao videoDao;
	
	public VideoService() {
		videoDao = new VideoDao();
	}
	
	public List<Video> getAll() {
		return videoDao.getAll();
	}
	
	public Video getById(int id) {
		
		return videoDao.getById(id);
	}
	
	public Video insert(Video video) {
		return videoDao.insert(video);
	}
	
	public boolean update(Video video) {
		return videoDao.update(video);
	}
	
	public boolean delete(int id) {
		return videoDao.delete(id);
	}
	
	public Video getByIdAndView(int id) {
		return videoDao.getByIdAndView(id);
	}
	
	public List<Video> findVideo(String search) {
		
		if (search == null) return new ArrayList<Video>();
		
		return videoDao.findVideo(search);
	}
}
