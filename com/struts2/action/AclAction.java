package com.struts2.action;

import org.apache.struts2.ServletActionContext;

import com.oa.PagerModel;
import com.oa.SystemContext;
import com.oa.manager.ModuleManager;
import com.oa.manager.RoleManager;
import com.oa.manager.UserManager;
import com.oa.model.ACL;

@SuppressWarnings("serial")
public class AclAction extends BaseAction {
	
	private ModuleManager moduleManager;
	
	private UserManager userManager;
	
	private RoleManager roleManager;
	
	private String principalType;
	
	private int principalSn;

	public String getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	public int getPrincipalSn() {
		return principalSn;
	}

	public void setPrincipalSn(int principalSn) {
		this.principalSn = principalSn;
	}
	
	//打开ACL授权界面
	//接受参数：principalType、principalSn
	//输出参数：模块列表、角色或用户
	@Override
	public String execute() throws Exception {
		System.out.println("--------------------");
		
		//如果主体类型是角色或用户
		if(ACL.TYPE_ROLE.equals(principalType)) {
			ServletActionContext.getRequest().setAttribute("role", roleManager.findRole(principalSn));
		} else if(ACL.TYPE_USER.equals(principalType)) {
			ServletActionContext.getRequest().setAttribute("user", userManager.findUser(principalSn));
		} else {
			throw new RuntimeException("不合法的主体类型");
		}
		
		//把类型传到页面判断
		ServletActionContext.getRequest().setAttribute("type", principalType);
		//把编号传到页面
		ServletActionContext.getRequest().setAttribute("sn", principalSn);
		//获取所有顶级模块列表
		SystemContext.setOffset(0);
		SystemContext.setPagesize(Integer.MAX_VALUE);
		PagerModel pm = moduleManager.searchModules(0);
		
		ServletActionContext.getRequest().setAttribute("modules", pm.getList());
		
		return SUCCESS;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}
	
}
