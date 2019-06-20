package com.oa.manager;

import java.util.List;

import com.oa.model.Module;

public interface AclManager {
	/**
	 * 授权接口
	 * @param principalType 主体类型
	 * @param principalSn 主体编号
	 * @param resourceSn 资源编号
	 * @param permission 权限 C/R/U/D
	 * @param yes 是否允许
	 */
	public void addOrModifyPermission(
			String principalType, 
			int principalSn, 
			int resourceSn, 
			int permission,
			boolean yes
			);
	/**
	 * 删除授权
	 * @param principalType
	 * @param principalSn
	 * @param resourceSn
	 */
	public void deletePermission(
			String principalType, 
			int principalSn, 
			int resourceSn
			);
	/**
	 * 添加或修改用户的继承特性
	 * @param userId
	 * @param resourceSn
	 * @param yes 是否继承
	 */
	public void addOrModifyUserExtends(int userId, int resourceSn, boolean yes);
	/**
	 * 即时判断用户对某模块的某操作是否拥有权限（允许或不允许）
	 * @param userId
	 * @param resourceSn
	 * @param permission
	 * @return
	 */
	public boolean hasPermission(int userId, int resourceSn, int permission);
	/**
	 * 查找用户拥有读取权限的模块列表（用于登陆，形成菜单导航栏）
	 * @param userId
	 * @return
	 */
	public List<Module> searchModules(int userId);
	//初始化表格
	public List<?> searchAclRecord(String principalType, int principalSn);
	//即时认证
	public boolean hasPermissionByResourceSn(int userId, String sn, int permission);
}
