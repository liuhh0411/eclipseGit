package com.struts2.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.oa.manager.AclManager;
import com.oa.model.Module;
import com.oa.model.User;

@SuppressWarnings("serial")
public class OutlookAction extends BaseAction {
	
	private AclManager aclManager;
	
	public void setAclManager(AclManager aclManager) {
		this.aclManager = aclManager;
	}
	
	//��outlook����
	@Override
	public String execute() throws Exception {
		//��ȡ��ǰ��½�û���������Ȩ
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("login");
		
		List<Module> list = aclManager.searchModules(user.getId());
		
//		System.out.println("--------------list:" + list.size());
//		System.out.println("--------------list:" + list.toString());
//		for(Module m : list) {
//			System.out.println(m.getId());
//		}
		
		ServletActionContext.getRequest().setAttribute("modules", list);
		
		return SUCCESS;
	}
	
}
