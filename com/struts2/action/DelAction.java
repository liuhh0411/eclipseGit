package com.struts2.action;

import com.oa.manager.OrgManager;

@SuppressWarnings("serial")
public class DelAction extends BaseAction {
	
	private OrgManager orgManager;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}
	
	@Override
	public String execute() throws Exception {
		
		orgManager.deleteOrg(id);
		
		return "del_success";
	}
	
}
