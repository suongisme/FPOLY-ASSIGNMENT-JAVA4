package poly.pt15307.sof3011.dao;

import java.util.List;

import org.dom4j.IllegalAddException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import poly.pt15307.sof3011.model.User;
import poly.pt15307.sof3011.utils.HibernateUtil;

public class UserDao {
	
	private SessionFactory sessionFactory;
	
	
	public UserDao() {
		sessionFactory = HibernateUtil.buildSessionFactory();
	}
	
	public List<User> getAll() {
		Session session = sessionFactory.openSession();
		
		List<User> userList = null;
		
		
		try (session) {
			userList = session.createQuery("FROM User", User.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	public User getById(int id) {
		Session session = sessionFactory.openSession();
		User user = null;
		
		try (session) {
			user = session.get(User.class, id);
			Hibernate.initialize(user.getVideosMap().values());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public boolean insert(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			session.save(user);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new IllegalAddException("email is exist!");
		} finally {
			session.close();
		}
		
		return true;
	}
	
	public boolean update(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			session.update(user);
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		
		return true;
	}
	
	public boolean delete(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			
			session.remove(getById(id));
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		
		return true;
	}
	
	public User login(String email, String password) {
		String hql = "FROM User u WHERE u.email=:email AND u.password=:password ";
		User user = null;
		
		Session session = sessionFactory.openSession();
		
		try {
			Query<User> query = session.createQuery(hql, User.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			List<User> resultList = query.getResultList();
			
			user = resultList.isEmpty() ? null : resultList.get(0);
			
			Hibernate.initialize(user.getVideosMap());
			Hibernate.initialize(user.getReacts());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user; 
	}
}
