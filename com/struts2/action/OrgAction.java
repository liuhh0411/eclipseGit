package com.struts2.action;

import org.apache.struts2.ServletActionContext;
import com.oa.PagerModel;
import com.oa.manager.OrgManager;
import com.oa.model.Organization;

@SuppressWarnings("serial")
public class OrgAction extends BaseAction {
	
	private int parentId;
	
	private boolean select;
	
	private OrgManager orgManager;
	
	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("------------------------------");
		System.out.println("parentId: " + parentId);
		System.out.println("pager.offset: " + ServletActionContext.getRequest().getParameter("pager.offset"));
		
//		int offset = 0;
//		try {
//			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
//			System.out.println("offset:" + offset);
//		} catch (RuntimeException e) {
//			System.out.println("类型转换异常");
//		}
		
		PagerModel pm = orgManager.findOrgs(parentId);
		
		System.out.println("pagerModel.listSize: " + pm.getList().size());
		System.out.println("pagerModel.total: " + pm.getTotal());
 		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("parentId", parentId);
		
		//返回
		int ppid = 0;
		if(parentId != 0) {
			Organization org = orgManager.findOrg(parentId);
			Organization parent = org.getParent();
			if(parent != null) {
				ppid = parent.getId();
			}
		}
		System.out.println("ppid: " + ppid);
		ServletActionContext.getRequest().setAttribute("ppid", ppid);
		
		if(select) {
			return "select";
		}
		
		return SUCCESS;
		
	}
	
	public String add() throws Exception {
		
		System.out.println("parentId: " + parentId);
		ServletActionContext.getRequest().setAttribute("parentId", parentId);
		return SUCCESS;
		
	}
	
	

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getParentId() {
		return parentId;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

}
