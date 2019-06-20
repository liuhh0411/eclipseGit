package com.oa.manager;

import java.util.List;

import com.oa.model.User;
import com.oa.model.UsersRoles;

public interface UserManager {
	
	/**
	 * ����û���Ϣ
	 * @param user
	 * @param personId
	 */
	public void addUser(User user,int personId);
	
	/**
	 * �����û���Ϣ
	 * @param user
	 * @param personId
	 */
	public void modifyUser(User user,int personId);
	
	/**
	 * ɾ���û���Ϣ
	 * @param userId
	 */
	public void deleteUser(int userId);
	
	/**
	 * �����ض����û�
	 * @param userId
	 * @return
	 */
	public User findUser(int userId);
	
	/**
	 * ��ѯ�û�ӵ�е����еĽ�ɫ
	 * @param userId �û�ID
	 * @return UsersRoles����ļ���
	 */
	public List<UsersRoles> searchUserRoles(int userId);
	
	/**
	 * ��ӻ�����û�ӵ�еĽ�ɫ������û�[userId]�Ѿ�ӵ�н�ɫ[roleId]��
	 * ����������ȼ�[orderNo]��������û�������Ӧ�Ľ�ɫ�����������ȼ�
	 * @param userId
	 * @param roleId
	 * @param orderNo
	 */
	public void addOrModifyUserRole(int userId,int roleId,int orderNum);
	
	/**
	 * ɾ��������û��Ľ�ɫ��������
	 * @param userId
	 * @param roleId
	 */
	public void deleteUserRole(int userId,int roleId);
	
	/**
	 * �û�ִ�е�½����
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username,String password);
}
