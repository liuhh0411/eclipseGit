package com.struts2.action;

import org.apache.struts2.ServletActionContext;

import com.oa.PagerModel;
import com.oa.manager.RoleManager;
import com.oa.model.Role;

@SuppressWarnings("serial")
public class RoleAction extends BaseAction {
	
	private RoleManager roleManager;
	
	private int id;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String execute() throws Exception {
		
		System.out.println("----------------------------");
		
		PagerModel pm = roleManager.searchRoles();
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		
		return SUCCESS;
	}
	
	public String add_role() throws Exception {
		
		Role role = new Role();
		role.setName(name);
		
		roleManager.addRole(role);
		
		return "add_success";
	}

public String del_role() throws Exception {
		
		roleManager.deleteRole(id);
		
		return "del_success";
	}
	
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
}
