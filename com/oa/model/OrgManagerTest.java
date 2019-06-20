package com.oa.model;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oa.manager.OrgManager;

import junit.framework.TestCase;

public class OrgManagerTest extends TestCase {
	
	private static BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");

	public void testAddOrg() {
		
		OrgManager orgManager = (OrgManager)factory.getBean("orgManager");
		Organization org = new Organization();
		org.setName("≤‚ ‘ª˙ππ");
		org.setDescription("√ª…∂√Ë ˆ");
		
		orgManager.addOrg(org, 0);
	}

	public void testDeleteOrg() {
		fail("Not yet implemented");
	}

	public void testModifyOrg() {
		fail("Not yet implemented");
	}

	public void testFindOrg() {
		fail("Not yet implemented");
	}

	public void testFindOrgs() {
		fail("Not yet implemented");
	}

}
