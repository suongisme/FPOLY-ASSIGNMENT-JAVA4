package poly.pt15307.sof3011.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	public static SessionFactory buildSessionFactory() {
		return new Configuration().configure().buildSessionFactory();
	}
	
}
