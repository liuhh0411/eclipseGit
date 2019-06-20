package com.struts2.action;

import org.apache.struts2.ServletActionContext;

import com.oa.manager.UserManager;
import com.oa.model.User;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {
	
	private UserManager userManager;
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("-----------------");
		
		User user = userManager.login(username, password);
		
		ServletActionContext.getRequest().getSession().setAttribute("login", user);
		
		return SUCCESS;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
}
