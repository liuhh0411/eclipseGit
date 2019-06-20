package com.oa.manager.impl;

import com.oa.PagerModel;
import com.oa.manager.OrgManager;
import com.oa.model.Organization;

public class OrgManagerImpl extends AbstractPagerManager implements OrgManager {

	public void addOrg(Organization org, int parentId) {
		//org == null
		if(parentId != 0) {
			org.setParent((Organization)this.getHibernateTemplate().
											load(Organization.class, parentId));
		}
		
		this.getHibernateTemplate().save(org);
		
		//TODO 设置Organization的唯一编号
		org.setSn(org.getParent() == null ? ("" + org.getId()) : (org.getParent().getSn() + "_" + org.getId()));
		
		this.getHibernateTemplate().update(org);
	}

	public void deleteOrg(int orgId) {
		
		Organization org = (Organization)this.getHibernateTemplate().load(Organization.class, orgId);
		
		if(org.getChildren().size() > 0) {
			throw new RuntimeException("存在子机构，不允许删除");
		}
		
		this.getHibernateTemplate().delete(org);
		
	}

	public void modifyOrg(Organization org, int parentId) {
		
		if(parentId != 0) {
			org.setParent((Organization)this.getHibernateTemplate().
					load(Organization.class, parentId));
		}
		
		this.getHibernateTemplate().update(org);
	}

	public Organization findOrg(int orgId) {
		
		return (Organization)this.getHibernateTemplate().load(Organization.class, orgId);
	}

	@SuppressWarnings("unchecked")
	public PagerModel findOrgs(int parentId) {
		/*
		//查询记录数
		String selectCountHql = "select count(*) from Organization org where org.parent is null";
		
		if(parentId != 0) {
			selectCountHql = "select count(*) from Organization org where org.parent.id = " + parentId;
		}
		//总记录数
		int total = ((Long)this.getSession().createQuery(selectCountHql).uniqueResult()).intValue();
		
		System.out.println("total:" + total);
		
		//查询机构列表
		String selectHql = "select org from Organization org where org.parent is null";
		
		if(parentId != 0) {
			selectHql = "select org from Organization org where org.parent.id = " + parentId;
		}
		
		List<Organization> list = this.getSession().createQuery(selectHql).setFirstResult(offset).setMaxResults(pagesize).list();
		System.out.println("listSize:" + list.size());
		
		PagerModel pm = new PagerModel();
		pm.setTotal(total);
		pm.setList(list);
		
		return pm;
		*/
		
		if(parentId == 0) {
			return this.searchPaginated("from Organization org where org.parent is null");
		}
		
		return this.searchPaginated("from Organization org where org.parent.id = ?", parentId);
	}
	
	
	
}
