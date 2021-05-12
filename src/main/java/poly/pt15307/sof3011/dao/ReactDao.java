package poly.pt15307.sof3011.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import poly.pt15307.sof3011.model.React;
import poly.pt15307.sof3011.model.User;
import poly.pt15307.sof3011.model.Video;
import poly.pt15307.sof3011.utils.HibernateUtil;

public class ReactDao {

	private SessionFactory sessionFactory;
	
	public ReactDao() {
		sessionFactory = HibernateUtil.buildSessionFactory();
	}
	
	public boolean insert(User user, Video video, boolean state) {
		System.out.println("dislike");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			
			React react = new React();
			react.setVideo(video);
			react.setUser(user);
			react.setLike(state);
			session.persist(react);
			
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	public boolean delete(User user, Video video, boolean state) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			React react = new React();
			react.setVideo(video);
			react.setUser(user);
			react.setLike(state);
			
			session.remove(react);
			
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	public boolean update(User user, Video video, boolean state) {
		System.out.println("remove dislike");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			React react = new React();
			react.setVideo(video);
			react.setUser(user);
			
			session.update(react);
			react.setLike(state);
			
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			session.close();
		}
		
	}
}
