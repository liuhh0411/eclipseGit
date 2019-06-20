package com.oa.model;

import java.util.Set;
/**
 * 
 * @author zudajun225
 * @hibernate.class table="t_organization"
 */
public class Organization {
	/**
	 * @hibernate.id
	 * 	 	generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property
	 */
	private String name;
	/**
	 * @hibernate.property
	 */
	private String sn;
	/**
	 * @hibernate.property
	 */
	private String description;
	/**
	 * @hibernate.many-to-one
	 * 		 column="pid"  not-found="ignore"
	 * 		 class="com.oa.model.Organization"
	 */
	private Organization parent;
	/**
	 * @hibernate.set inverse="true" lazy="extra"
	 * @hibernate.key column="pid"
	 * @hibernate.one-to-many class="com.oa.model.Organization"
	 */
	private Set<Organization> children;

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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Organization getParent() {
		return parent;
	}
	
	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public Set<Organization> getChildren() {
		return children;
	}

	public void setChildren(Set<Organization> children) {
		this.children = children;
	}
	
}
