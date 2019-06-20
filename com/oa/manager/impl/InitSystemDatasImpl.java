package com.oa.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.oa.manager.InitSystemDatas;
import com.oa.manager.OrgManager;
import com.oa.manager.UserManager;
import com.oa.model.ACL;
import com.oa.model.Module;
import com.oa.model.Organization;
import com.oa.model.Permission;
import com.oa.model.Person;
import com.oa.model.Role;
import com.oa.model.User;

public class InitSystemDatasImpl extends AbstractPagerManager implements
		InitSystemDatas {
	private static Log logger = LogFactory.getLog(InitSystemDatasImpl.class);
	private String file;
	private OrgManager orgManager;
	private UserManager userManager;
	
	@SuppressWarnings("unchecked")
	public void addOrUpdateInitDatas(String xmlFilePath){
		try {
			String filePath = null;
			if(xmlFilePath == null || xmlFilePath.trim().equals("")){
				filePath = file;
			}else{
				filePath = xmlFilePath;
			}
			
			//DOM4J使用示例
			Document document = new SAXReader().read(
				Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)
			);
			
			importModules(document.selectNodes("//Modules/Module") ,null);
			importRoleAndAcl(document.selectNodes("//Roles/Role"));
			importOrgAndPerson(document.selectNodes("//Organizations/Org"),null);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("初始化数据生成有误！");
		}
	}

	
	//导入模块信息
	@SuppressWarnings("unchecked")
	protected void importModules(List<Element> modules,Module parent){
		
		for (Element element : modules) {
			Module module = new Module();
			module.setName(element.attributeValue("name"));
			module.setSn(element.attributeValue("sn"));
			module.setUrl(element.attributeValue("url"));
			module.setOrderNum(Integer.parseInt(element.attributeValue("orderNum")));
			module.setParent(parent);
			getHibernateTemplate().save(module);
			logger.info("导入模块【"+module.getName()+"】");
			importModules(element.selectNodes("Module") , module);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void importRoleAndAcl(List<Element> roles){
		for (Element element : roles) {
			Role role = new Role();
			role.setName(element.attributeValue("name"));
			this.getHibernateTemplate().save(role);
			
			//给角色授权
			List<Element> acls = element.selectNodes("Acl");
			for (Element aclElem : acls) {
				Integer moduleId = 
					(Integer)getSession()
					.createQuery("select m.id from Module m where m.name = ?")
					.setParameter(0, aclElem.attributeValue("module"))
					.uniqueResult();
				ACL acl = new ACL();
				acl.setPrincipalType(ACL.TYPE_ROLE);
				acl.setPrincipalSn(role.getId());
				acl.setResourceSn(moduleId);
				if("true".equals(aclElem.attributeValue("C"))){
					acl.setPermission(Permission.CREATE, true);
				}
				if("true".equals(aclElem.attributeValue("R"))){
					acl.setPermission(Permission.READ, true);
				}
				if("true".equals(aclElem.attributeValue("U"))){
					acl.setPermission(Permission.UPDATE, true);
				}
				if("true".equals(aclElem.attributeValue("D"))){
					acl.setPermission(Permission.DELETE, true);
				}
				this.getHibernateTemplate().save(acl);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void importOrgAndPerson(List<Element> orgs,Organization parent){
		for (Element element : orgs) {
			Organization org = new Organization();
			org.setName(element.attributeValue("name"));
			System.out.println("orgManager");
			if(parent == null) {
				orgManager.addOrg(org, 0);
			} else {
				orgManager.addOrg(org, parent.getId());
			}
			
			
			//查找机构下的人员信息，并初始化
			List<Element> persons = element.selectNodes("Person");
			for (Element personElem : persons) {
				Person person = new Person();
				person.setName(personElem.attributeValue("name"));
				person.setOrg(org);
				getHibernateTemplate().save(person);
				
				//给人员分配登陆帐号
				User user = new User();
				user.setUsername(personElem.attributeValue("username"));
				user.setPassword(personElem.attributeValue("password"));
				user.setPerson(person);
				getHibernateTemplate().save(user);
				
				//给用户分配角色
				String roles = personElem.attributeValue("roles");
				String[] roleNames = roles.split(",");
				for(int i=0; i<roleNames.length; i++){
					int roleId = 
						(Integer)getSession()
						.createQuery("select r.id from Role r where r.name = ?")
						.setParameter(0, roleNames[i])
						.uniqueResult();
					userManager.addOrModifyUserRole(user.getId(), roleId, i+1);
				}
			}
			
			//初始化此机构下的子机构信息
			importOrgAndPerson( element.selectNodes("Org") , org);
		}
	}
	
	
	public void setFile(String file) {
		this.file = file;
	}


	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}


	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
