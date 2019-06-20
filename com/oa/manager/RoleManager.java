package com.oa.manager;

import com.oa.PagerModel;
import com.oa.model.Role;

public interface RoleManager {
	
	/**
	 * 添加角色
	 * @param role
	 */
	public void addRole(Role role);
	
	/**
	 * 删除角色
	 * @param roleId
	 */
	public void deleteRole(int roleId);
	
	public void modifyRole(Role role);
	
	public Role findRole(int roleId);

	/**
	 * 分页查询角色的信息
	 * @return
	 */
	public PagerModel searchRoles();
	
	public String method();
}
