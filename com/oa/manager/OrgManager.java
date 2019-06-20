package com.oa.manager;


import com.oa.PagerModel;
import com.oa.model.Organization;

public interface OrgManager {
	
	public void addOrg(Organization org, int parentId);
	
	public void deleteOrg(int orgId);
	
	public void modifyOrg(Organization org, int parentId);
	
	public Organization findOrg(int orgId);
	
	//查找机构列表，如果parentId为0，则查找顶级机构列表
	public PagerModel findOrgs(int parentId);
	
}
