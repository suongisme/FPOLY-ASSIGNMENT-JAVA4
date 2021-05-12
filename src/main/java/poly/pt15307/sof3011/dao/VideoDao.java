package poly.pt15307.sof3011.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import poly.pt15307.sof3011.model.Video;
import poly.pt15307.sof3011.utils.HibernateUtil;

public class VideoDao {
	
	private SessionFactory sessionFactory;
	
	public VideoDao() {
		sessionFactory = HibernateUtil.buildSessionFactory();
	}
	
	public List<Video> getAll() {
		Session session = sessionFactory.openSession();
		
		List<Video> videoList = null;
		
		try (session) {
			videoList = session.createQuery("FROM Video v JOIN FETCH v.author", Video.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return videoList;
	}
	
	public Video getById(int id) {
		Session session = sessionFactory.openSession();
		Video video = null;
		
		try (session) {
			video = session.get(Video.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return video;
	}
	
	public Video insert(Video video) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try (session) {
			
			session.save(video);
			video.setThumbnailUrl("user-image/video/id-"+video.getId()+".png");
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		}
		
		return video;
	}
	
	public boolean update(Video video) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try (session) {
			
			session.update(video);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean delete(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try (session) {
			
			session.remove(getById(id));
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Video getByIdAndView(int id) {
		Session session = sessionFactory.openSession();
		Video video = null;
		Transaction transaction = session.beginTransaction();
		try (session) {
			video = session.get(Video.class, id);
			video.setView(video.getView() + 1);
			
			Hibernate.initialize(video.getAuthor().getVideosMap());
			Hibernate.initialize(video.getReact());
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		
		return video;
	}
	
	public List<Video> findVideo(String search) {
		Session session = sessionFactory.openSession();
		List<Video> videos = null;
		try (session) {
			
			Query<Video> query = 
					session.createQuery("FROM Video v JOIN FETCH v.uploadBy WHERE v.title like :title", Video.class);
			
			query.setParameter("title", "%"+search+"%");
			
			videos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return videos;
		
	}
}
