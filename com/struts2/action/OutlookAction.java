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
	
	//打开outlook界面
	@Override
	public String execute() throws Exception {
		//获取当前登陆用户的所有授权
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
