package com.oa;

import com.oa.manager.AclManager;

//��û�гɹ�
//JSTL���������Ȩ�޵ļ�ʱ��֤
public class SecurityFunction {
	
	private static AclManager aclManager;
	
	public static boolean method(int userId,String sn,int permission) {
		
		return aclManager.hasPermissionByResourceSn(userId, sn, permission);
	}

	public void setAclManager(AclManager aclManager) {
		SecurityFunction.aclManager = aclManager;
	}
}
