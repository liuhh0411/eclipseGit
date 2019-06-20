package com.struts2.action;

import org.apache.struts2.ServletActionContext;

import com.oa.PagerModel;
import com.oa.manager.ModuleManager;
import com.oa.model.Module;

@SuppressWarnings("serial")
public class ModuleAction extends BaseAction {
	
	private ModuleManager moduleManager;
	
	private int id;
	
	private String name;
	
	private String sn;
	
	private String url;
	
	private int orderNum;
	
	private int parentId;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("------------------------------");
		System.out.println("parentId: " + parentId);
		System.out.println("pager.offset: " + ServletActionContext.getRequest().getParameter("pager.offset"));
		
		PagerModel pm = moduleManager.searchModules(parentId);
		
		System.out.println("pagerModel.listSize: " + pm.getList().size());
		System.out.println("pagerModel.total: " + pm.getTotal());
 		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("parentId", parentId);
		
		//·µ»Ø
		int ppid = 0;
		if(parentId != 0) {
			Module module = moduleManager.findModule(parentId);
			Module parent = module.getParent();
			if(parent != null) {
				ppid = parent.getId();
			}
		}
		System.out.println("ppid: " + ppid);
		ServletActionContext.getRequest().setAttribute("ppid", ppid);
		
		return SUCCESS;
	}

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}
	
	public String add_module() throws Exception {
		
		Module module = new Module();
		module.setName(name);
		module.setSn(sn);
		module.setOrderNum(orderNum);
		module.setUrl(url);
		
		moduleManager.addModule(module, parentId);
		
		return "add_success";
	}
	
public String del_module() throws Exception {
		
		moduleManager.deleteModule(id);
		
		return "del_success";
	}
	
}
