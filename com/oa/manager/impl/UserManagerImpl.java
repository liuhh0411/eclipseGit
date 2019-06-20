package com.oa.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.oa.manager.UserManager;
import com.oa.model.Person;
import com.oa.model.Role;
import com.oa.model.User;
import com.oa.model.UsersRoles;

public class UserManagerImpl extends AbstractPagerManager implements
		UserManager {

	public void addOrModifyUserRole(int userId, int roleId, int orderNum) {
		//����userId��roleId����UsersRoles����
		UsersRoles ur = this.findUsersRoles(userId, roleId);
		
		if(ur == null) {
			ur = new UsersRoles();
			ur.setOrderNum(orderNum);
			ur.setUser((User)this.getHibernateTemplate().load(User.class, userId));
			ur.setRole((Role)this.getHibernateTemplate().load(Role.class, roleId));
			
			this.getHibernateTemplate().save(ur);
			return;
		}
		//
		ur.setOrderNum(orderNum);
		this.getHibernateTemplate().update(ur);
	}

	public void addUser(User user, int personId) {
		if(personId == 0) {
			throw new RuntimeException("����ѡ����Ӧ����Ա��Ϣ");
		}
		user.setPerson((Person)this.getHibernateTemplate().load(Person.class, personId));
		
		//�������ʱ��
		user.setCreateTime(new Date());
		
		this.getHibernateTemplate().save(user);
	}

	public void deleteUser(int userId) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(User.class, userId));

	}

	public void deleteUserRole(int userId, int roleId) {
		this.getHibernateTemplate().delete(this.findUsersRoles(userId, roleId));

	}

	public User findUser(int userId) {
		
		return (User)this.getHibernateTemplate().load(User.class, userId);
	}

	public User login(String username, String password) {
		User user = (User)this.getSession().createQuery(
									"select u from User u where u.username = ?")
									.setParameter(0, username)
									.uniqueResult();
		
		if(user == null) {
			throw new RuntimeException("�����ڸ��û�");
		}
		
		if(!user.getPassword().equals(password)) {
			throw new RuntimeException("�������");
		}
		
		if(user.getExpireTime() != null) {
			//����ʱ��
			Calendar now = Calendar.getInstance();
			
			//ʧЧʱ��
			Calendar expireTime = Calendar.getInstance();
			expireTime.setTime(user.getExpireTime());
			
			if(now.after(expireTime)) {
				throw new RuntimeException("���û��ѹ���");
			}
		}
		
		return user;
	}

	public void modifyUser(User user, int personId) {
		System.out.println("personId:" + personId);
		user.setPerson((Person)this.getHibernateTemplate().load(Person.class, personId));
		this.getHibernateTemplate().update(user);

	}

	@SuppressWarnings("unchecked")
	public List<UsersRoles> searchUserRoles(int userId) {
		
		return this.getHibernateTemplate().find("select ur from UsersRoles ur where ur.user.id = ?", userId);
	}
	
	public UsersRoles findUsersRoles(int userId, int roleId) {
		return (UsersRoles)this.getSession().createQuery(
		"select ur from UsersRoles ur where ur.user.id = ? and ur.role.id = ?")
		.setParameter(0, userId)
		.setParameter(1, roleId)
		.uniqueResult();
	}

}
