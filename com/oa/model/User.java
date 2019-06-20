package com.oa.model;

import java.util.Date;

/**
 * 
 * @author zudajun225
 * @hibernate.class table="t_user"
 */
public class User {
	/**
	 * @hibernate.id
	 * 		generator-class="native"
	 */
	private int id;
	/**
	 * ��½�˺�
	 * @hibernate.property
	 * 			not-null="true"
	 * 			unique="true"
	 */
	private String username;
	/**
	 * ��½����
	 * @hibernate.property
	 * 			not-null="true"
	 */
	private String  password;
	/**
	 * ����ʱ��
	 * @hibernate.property update="false"
	 */
	private Date createTime;
	/**
	 * ʧЧʱ��
	 * @hibernate.property
	 */
	private Date expireTime;
	/**
	 * ��Ա��Ϣ
	 * @hibernate.many-to-one
	 * 			unique="true"
	 */
	private Person person;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
}
