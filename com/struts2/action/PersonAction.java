package com.struts2.action;

import org.apache.struts2.ServletActionContext;

import com.oa.PagerModel;
import com.oa.manager.PersonManager;
import com.oa.model.Person;

@SuppressWarnings("serial")
public class PersonAction extends BaseAction {
	
	private PersonManager personManager;
	
	private int id;

	private String name;

	private String sex;

	private int orgId;

	private String duty;

	private String address;
	

	private String phone;
	

	private String description;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public int getOrgId() {
		return orgId;
	}


	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}


	public String getDuty() {
		return duty;
	}


	public void setDuty(String duty) {
		this.duty = duty;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}


	@Override
	public String execute() throws Exception {
		
		System.out.println("-----------------------");
		
		PagerModel pm = personManager.searchPersons();
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		return SUCCESS;
	}
	
	public String toAdd() throws Exception {
		return SUCCESS;
	}
	
	public String addPerson() throws Exception {
		
		Person person = new Person();
		person.setName(name);
		person.setSex(sex);
		person.setDuty(duty);
		person.setAddress(address);
		person.setDescription(description);
		person.setPhone(phone);
		
		personManager.addPerson(person, orgId);
		
		return "add_success";
	}
	
	public String delPerson() throws Exception {
	
		System.out.println("id: " + id);
		personManager.deletePerson(id);
		
		return "del_success";
	}
	
}
