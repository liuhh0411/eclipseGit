package com.oa.manager.impl;

import com.oa.PagerModel;
import com.oa.manager.PersonManager;
import com.oa.model.Organization;
import com.oa.model.Person;

public class PersonManagerImpl extends AbstractPagerManager implements
		PersonManager {

	public void addPerson(Person person, int orgId) {

		if(orgId == 0){
			throw new RuntimeException("机构不允许为空！");
		}
		
		person.setOrg(
				(Organization)getHibernateTemplate().load(Organization.class, orgId)
			);
		getHibernateTemplate().save(person);
	}

	public void deletePerson(int personId) {
		getHibernateTemplate().delete(
				getHibernateTemplate().load(Person.class, personId)
			);
	}

	public Person findPerson(int personId) {
		return (Person)getHibernateTemplate().load(Person.class, personId);
	}

	public PagerModel searchPersons() {
		return searchPaginated("from Person");
	}

	public PagerModel searchPersons(int orgId) {
		return searchPaginated("select p from Person p where p.org.id = "+orgId);
	}

}
