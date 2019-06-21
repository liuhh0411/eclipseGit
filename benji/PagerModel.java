package com.oa;

import java.util.Collections;
import java.util.List;

import com.oa.model.Organization;

/**
 * 
 * @author zudajun225
 *
 */
public class PagerModel {
	
	/**
	 * 总记录数
	 */
	private int total;
	
	//当前页的结果集
	private List<Organization> list;

	public int getTotal() {
		return total;
	}
	// set get total 分支提交
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * 返回Organization的集合
	 * @return List&lt;Organization&gt; (size >=0)
	 */
	public List<Organization> getList() {
		return list == null? Collections.<Organization>emptyList() : list;
	}

	public void setList(List<Organization> list) {
		this.list = list;
	}
}
