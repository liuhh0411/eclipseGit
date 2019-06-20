package com.oa.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oa.manager.AclManager;
import com.oa.model.ACL;
import com.oa.model.Module;
import com.oa.model.Permission;

public class AclManagerImpl extends HibernateDaoSupport implements AclManager {
	
	//��Ȩ
	public void addOrModifyPermission(String principalType, int principalSn,
			int resourceSn, int permission, boolean yes) {
		//���������ʶ����Դ��ʶ����ACLʵ��
		ACL acl = this.findAcl(principalType, principalSn, resourceSn);
		
		//�������ACLʵ���������
		if(acl != null) {
			acl.setPermission(permission, yes);
			this.getHibernateTemplate().update(acl);
			return;
		}
		
		//������ACLʵ��
		acl = new ACL();
		acl.setPrincipalType(principalType);
		acl.setPrincipalSn(principalSn);
		acl.setResourceSn(resourceSn);
		acl.setPermission(permission, yes);
		
		this.getHibernateTemplate().save(acl);

	}
	
	//�����û��ļ̳�����
	//true��ʾ�̳У�false��ʾ���̳�
	public void addOrModifyUserExtends(int userId, int resourceSn, boolean yes) {
		//���������ʶ����Դ��ʶ����ACLʵ��
		ACL acl = this.findAcl(ACL.TYPE_USER, userId, resourceSn);
		
		//�������ACLʵ���������
		if(acl != null) {
			acl.setExtends(yes);
			this.getHibernateTemplate().update(acl);
			return;
		}
		
		//������ACLʵ��
		acl = new ACL();
		acl.setPrincipalType(ACL.TYPE_USER);
		acl.setPrincipalSn(userId);
		acl.setResourceSn(resourceSn);
		acl.setExtends(yes);
		
		this.getHibernateTemplate().save(acl);

	}
	
	//ɾ��
	public void deletePermission(String principalType, int principalSn,
			int resourceSn) {
		this.getHibernateTemplate().delete(this.findAcl(principalType, principalSn, resourceSn));

	}
	
	//��ʱ��֤
	@SuppressWarnings("unchecked")
	public boolean hasPermission(int userId, int resourceSn, int permission) {
		//����ֱ�������û�����Ȩ
		ACL acl = this.findAcl(ACL.TYPE_USER, userId, resourceSn);
		
		if(acl != null) {
			int result = acl.getPermission(permission);
			if(result != ACL.ACL_NEUTRAL) {
				return result == ACL.ACL_YES ? true : false;
			}
		}
		
		//���������û��Ľ�ɫ��Ȩ
		String hql = "select r.id from UsersRoles ur join ur.role r join ur.user u " +
				"where u.id = ? order by ur.orderNum";
		
		List<Integer> aclIds = this.getHibernateTemplate().find(hql, userId);
		//���ս�ɫ���ȼ�������ACLʵ��
		for(int id : aclIds) {
			acl = this.findAcl(ACL.TYPE_USER, id, resourceSn);
			if(acl != null) {
				return acl.getPermission(permission) == ACL.ACL_YES ? true : false;
			}
		}
		
		return false;
	}
	
	public boolean hasPermissionByResourceSn(int userId, String sn, int permission){
		
		String hql = "select m.id from Module m where m.sn = ?";
		
		return this.hasPermission(
							userId, 
							(Integer)this.getSession().createQuery(hql).setParameter(0, sn).uniqueResult(), 
							permission);
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> searchModules(int userId) {
		
		Map<Integer, ACL> temp = new HashMap<Integer, ACL>();
		//�������ȼ��ӵ͵��߲����û�ӵ�еĽ�ɫ
		String hql = "select r.id from UsersRoles ur join ur.role r join ur.user u " +
				"where u.id = ? order by ur.orderNum desc";
		
		List<Integer> rIds = this.getHibernateTemplate().find(hql, userId);
		
		//���λ�ý�ɫ��Ȩ���б�
		for(int rId : rIds) {
			List<ACL> acls = this.findRoleACLs(rId);
			//����Ȩ������ʱ����
			for(ACL acl : acls) {
				temp.put(acl.getResourceSn(), acl);
			}
		}
		
		//����ֱ�������û�����Ȩ�б�
		List<ACL> acls = this.findUserAcls(userId);
		for(ACL acl : acls) {
			temp.put(acl.getResourceSn(), acl);
		}
		
		//�Ѿ�����û���ӵ�е�������Ȩ
		//ȥ��û�ж�ȡȨ�޵���Ȩ
		//��ʱ����
		List<Integer> delR = new ArrayList<Integer>();
		Set<Map.Entry<Integer, ACL>> entries = temp.entrySet();
		for(Map.Entry<Integer, ACL> entry : entries) {
			
			ACL acl = entry.getValue();
			
			if(acl.getPermission(Permission.READ) == ACL.ACL_NO) {
				delR.add(entry.getKey());
			}
		}
		
		for(Integer key : delR) {
			System.out.println(key);
		}
		
		//ɾ��û�ж�ȡȨ�޵���Ȩ
		for(Integer key : delR) {
			temp.remove(key);
		}
		//�ж�
		if(temp.isEmpty()) {
			return new ArrayList<Module>();
		}
		
		String searchModules = "select m from Module m where m.id in (:ids)";
		
		return this.getSession().createQuery(searchModules)
										.setParameterList("ids", temp.keySet())
										.list();
	}
	
	//�����û�����ֱ�������û���Ȩ���б�����Ԫ����ACLʵ����ע�⣺��Ȩ���Ǽ̳еģ���Ӧ�ð������б��У�
	@SuppressWarnings({ "unchecked", "unused" })
	private List<ACL> findUserAcls(int userId) {
		String hql = "select acl from ACL acl where acl.principalType = ? " +
		"and acl.principalSn = ? and acl.extendsState = 0";
		
		return this.getHibernateTemplate().find(hql, new Object[]{ACL.TYPE_USER,userId});
	}
	
	
	//���ݽ�ɫ���ҽ�ɫ����Ȩ�б������б��Ԫ���ǣ�ACL
	@SuppressWarnings({ "unused", "unchecked" })
	private List<ACL> findRoleACLs(int roleId) {
		String hql = "select acl from ACL acl where acl.principalType = ? " +
				"and acl.principalSn = ?";
		
		return this.getHibernateTemplate().find(hql, new Object[]{ACL.TYPE_ROLE,roleId});
	}
	
	//���ҷ���������ACLʵ��
	private ACL findAcl(String principalType, int principalSn,
			int resourceSn) {
		
		return (ACL)this.getSession().createQuery(
				"select acl from ACL acl where acl.principalType = ? " +
				"and acl.principalSn = ? and acl.resourceSn = ?")
				.setParameter(0, principalType)
				.setParameter(1, principalSn)
				.setParameter(2, resourceSn)
				.uniqueResult();
	}
	
	public List<?> searchAclRecord(String principalType, int principalSn) {
		
		String sql = "select resourceSn,aclState&1,aclState&2," +
					"aclState&4,aclState&8,extendsState " +
					"from t_acl where principalType = '"+principalType + 
					"' and principalSn = "+principalSn;

		return this.getSession().createSQLQuery(sql).list();
	}
	

}
