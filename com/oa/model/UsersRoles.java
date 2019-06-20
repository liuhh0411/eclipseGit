package com.oa.model;

/**
 * 
 * @author zudajun225
 * @hibernate.class table="t_ur"
 */
public class UsersRoles {
	/**
	 * @hibernate.id
	 * 		generator-class="native"
	 */
	private int id;
	/**
	 * ��Ӧ�Ľ�ɫ
	 * @hibernate.many-to-one
	 */
	private Role role;
	/**
	 * ��Ӧ���û�
	 * @hibernate.many-to-one
	 */
	private User user;
	/**
	 * ��ɫ������û��е����ȼ�
	 * @hibernate.property
	 */
	private int orderNum;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
}
