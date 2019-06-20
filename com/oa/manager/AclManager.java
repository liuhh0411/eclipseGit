package com.oa.manager;

import java.util.List;

import com.oa.model.Module;

public interface AclManager {
	/**
	 * ��Ȩ�ӿ�
	 * @param principalType ��������
	 * @param principalSn ������
	 * @param resourceSn ��Դ���
	 * @param permission Ȩ�� C/R/U/D
	 * @param yes �Ƿ�����
	 */
	public void addOrModifyPermission(
			String principalType, 
			int principalSn, 
			int resourceSn, 
			int permission,
			boolean yes
			);
	/**
	 * ɾ����Ȩ
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
	 * ��ӻ��޸��û��ļ̳�����
	 * @param userId
	 * @param resourceSn
	 * @param yes �Ƿ�̳�
	 */
	public void addOrModifyUserExtends(int userId, int resourceSn, boolean yes);
	/**
	 * ��ʱ�ж��û���ĳģ���ĳ�����Ƿ�ӵ��Ȩ�ޣ����������
	 * @param userId
	 * @param resourceSn
	 * @param permission
	 * @return
	 */
	public boolean hasPermission(int userId, int resourceSn, int permission);
	/**
	 * �����û�ӵ�ж�ȡȨ�޵�ģ���б����ڵ�½���γɲ˵���������
	 * @param userId
	 * @return
	 */
	public List<Module> searchModules(int userId);
	//��ʼ�����
	public List<?> searchAclRecord(String principalType, int principalSn);
	//��ʱ��֤
	public boolean hasPermissionByResourceSn(int userId, String sn, int permission);
}
