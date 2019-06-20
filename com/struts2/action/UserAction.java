package com.struts2.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.oa.PagerModel;
import com.oa.manager.PersonManager;
import com.oa.manager.RoleManager;
import com.oa.manager.UserManager;
import com.oa.model.User;
import com.oa.model.UsersRoles;

@SuppressWarnings("serial")
public class UserAction extends BaseAction {
	
	private UserManager userManager;
	
	private PersonManager personManager;
	
	private RoleManager roleManager;
	
	private int id;
	
	private String username;
	
	private String password;
	
	private Date createTime;
	
	private Date expireTime;
	
	private int personId;
	
	//此字段用于给用户分配角色时：角色标识
	private int roleId;
	
	//此字段用户给用户分配角色时：优先级
	private int orderNum;

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

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("-----------------");
		PagerModel pm = personManager.searchPersons();
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		
		return SUCCESS;
	}
	
	public String add_user() throws Exception {
		
		System.out.println("-----------------");
		System.out.println("expireTime:" + expireTime);
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setExpireTime(expireTime);
		
		userManager.addUser(user, personId);
		return "add_success";
	}
	
	public String del_user() throws Exception {
		
		userManager.deleteUser(id);
		
		return "del_success";
	}
	
	public String modify_user() throws Exception {
		
		User user = new User();
		//关键
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setExpireTime(expireTime);
		
		userManager.modifyUser(user, personId);
		
		return "modify_success";
	}
	
	public String userRole() throws Exception {
		
		User user = userManager.findUser(id);
		
		List<UsersRoles> urs = userManager.searchUserRoles(id);
		
		ServletActionContext.getRequest().setAttribute("user", user);
		ServletActionContext.getRequest().setAttribute("urs", urs);
		
		return SUCCESS;
	}
	
	public String toUserRole() throws Exception {
		System.out.println("-----------------");
		
		PagerModel pm = roleManager.searchRoles();
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		System.out.println("pm.size:" + pm.getList().size());
		ServletActionContext.getRequest().setAttribute("id", id);
		System.out.println("id:" + id);
		
		return SUCCESS;
	}
	
	public String add_userRole() throws Exception {
		
		userManager.addOrModifyUserRole(id, roleId, orderNum);
		
		return "add_success";
	}
	
	public String del_userRole() throws Exception {
		
		userManager.deleteUserRole(id, roleId);
		
		return "del_success";
	}
	
}
