package com.oa.manager;

import com.oa.PagerModel;
import com.oa.model.Module;

public interface ModuleManager {
	
	/**
	 * ���ģ����Ϣ�������ģ���IDΪ0������Ӷ���ģ��
	 * @param module ģ����Ϣ
	 * @param parentid ��ģ���ID
	 */
	public void addModule(Module module,int parentId);
	
	/**
	 * ɾ��ģ��
	 * @param moduleId
	 */
	public void deleteModule(int moduleId);
	
	/**
	 * ����ģ����Ϣ
	 * @param module
	 * @param parentid
	 */
	public void modifyModule(Module module,int parentId);
	
	/**
	 * ��ѯ�ض���ģ��
	 * @param moduleId
	 * @return
	 */
	public Module findModule(int moduleId);
	
	/**
	 * ��ҳ��ѯģ�����Ϣ
	 * @param parentId
	 * @return
	 */
	public PagerModel searchModules(int parentId);
}
