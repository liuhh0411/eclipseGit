package com.oa.manager;


import com.oa.PagerModel;
import com.oa.model.Organization;

public interface OrgManager {
	
	public void addOrg(Organization org, int parentId);
	
	public void deleteOrg(int orgId);
	
	public void modifyOrg(Organization org, int parentId);
	
	public Organization findOrg(int orgId);
	
	//���һ����б����parentIdΪ0������Ҷ��������б�
	public PagerModel findOrgs(int parentId);
	
}
