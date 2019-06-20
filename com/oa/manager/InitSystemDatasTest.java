package com.oa.manager;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oa.manager.InitSystemDatas;

import junit.framework.TestCase;

public class InitSystemDatasTest extends TestCase {
	private static BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
	public void testAddOrUpdateInitDatas() {
		InitSystemDatas init = (InitSystemDatas)factory.getBean("initSystemDatas");
		init.addOrUpdateInitDatas("init_data.xml");
	}

}
