package com.oa.manager;

import com.oa.PagerModel;
import com.oa.model.Role;

public interface RoleManager {
	
	/**
	 * ��ӽ�ɫ
	 * @param role
	 */
	public void addRole(Role role);
	
	/**
	 * ɾ����ɫ
	 * @param roleId
	 */
	public void deleteRole(int roleId);
	
	public void modifyRole(Role role);
	
	public Role findRole(int roleId);

	/**
	 * ��ҳ��ѯ��ɫ����Ϣ
	 * @return
	 */
	public PagerModel searchRoles();
	
	public String method();
}
