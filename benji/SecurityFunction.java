package com.oa;

import com.oa.manager.AclManager;

//并没有成功
//JSTL函数，完成权限的即时认证
public class SecurityFunction {
	
	private static AclManager aclManager;
	
	public static boolean method(int userId,String sn,int permission) {
		
		return aclManager.hasPermissionByResourceSn(userId, sn, permission);
	}

	public void setAclManager(AclManager aclManager) {
		SecurityFunction.aclManager = aclManager;
	}
}
