package com.struts2.action;

import org.apache.struts2.ServletActionContext;

import com.oa.model.User;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		//添加权限认证代码
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("login");
		if(user == null) {
			return "login";
		}
		
		return super.execute();
	}
	
}
