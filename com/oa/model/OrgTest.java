package com.oa.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import junit.framework.TestCase;

public class OrgTest extends TestCase {
	
	public void testSaveOrg1() {
		
		Session session = null;
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Set<Organization> set = new HashSet<Organization>();
			
			
			Organization org2 = new Organization();
			org2.setName("org2");
			set.add(org2);
			
			Organization org3 = new Organization();
			org3.setName("org3");
			set.add(org3);
			
			Organization org4 = new Organization();
			org4.setName("org4");
			set.add(org4);
			
			Organization org1 = new Organization();
			org1.setName("org1");
			org1.setChildren(set);
			
			org2.setParent(org1);
			org3.setParent(org1);
			org4.setParent(org4);
			
			session.save(org1);
			session.save(org2);
			session.save(org3);
			session.save(org4);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
public void testLoadOrg() {
		
		Session session = null;
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			//child --> parent
			Organization org2 = (Organization)session.load(Organization.class, 2);
			System.out.println(org2.getName() + "," + org2.getParent().getName());
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

public void testLoadOrg2() {
	
	Session session = null;
	
	try {
		session = HibernateUtils.getSession();
		session.beginTransaction();
		//parent --> child
		Organization org1 = (Organization)session.load(Organization.class, 1);
		Set<Organization> set = org1.getChildren();
		for(Organization org : set) {
			System.out.println(org.getName());
		}
		
		session.getTransaction().commit();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
}
