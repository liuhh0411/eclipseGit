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
	 * �ܼ�¼��
	 */
	private int total;
	
	//��ǰҳ�Ľ����
	private List<Organization> list;

	public int getTotal() {
		return total;
	}
	
	// get set 20190621 14��40
	// �ϲ���ʱ��ֱ�Ӿ��޸��� 20190621 14��42
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * ����Organization�ļ���
	 * @return List&lt;Organization&gt; (size >=0)
	 */
	public List<Organization> getList() {
		return list == null? Collections.<Organization>emptyList() : list;
	}

	public void setList(List<Organization> list) {
		this.list = list;
	}
}