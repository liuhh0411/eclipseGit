package com.struts2.action;

import com.oa.manager.OrgManager;
import com.oa.model.Organization;

@SuppressWarnings("serial")
public class AddAction extends BaseAction {
	
	private OrgManager  orgManager;
	
	private int id;
	
	private String name;
	
	private String sn;
	
	private String description;
	
	private int parentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String execute() throws Exception {
		
		Organization org = new Organization();
		org.setName(name);
		org.setDescription(description);
		
		orgManager.addOrg(org, parentId);
		
		return "add_success";
		
	}

	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}
	
	
	
}
