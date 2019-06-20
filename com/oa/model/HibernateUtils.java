package com.oa.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static SessionFactory factory;
	
	static {
		try {
			Configuration cfg = new Configuration().configure();
			factory = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SessionFactory getSessionFactory() {
		
		return factory;
	}
	
	public static Session getSession() {
		
		return factory.openSession();
	}
	
	public static void closeSessionFactory(Session session) {
		
		if(session != null) {
			if(session.isOpen()) {
				session.close();
			}
		}
	}
	
}
