package com.oa.manager.impl;

import com.oa.PagerModel;
import com.oa.manager.ModuleManager;
import com.oa.model.Module;

public class ModuleManagerImpl extends AbstractPagerManager implements
		ModuleManager {

	public void addModule(Module module, int parentId) {
		if(parentId != 0) {
			module.setParent((Module)this.getHibernateTemplate().load(Module.class, parentId));
		}
		
		this.getHibernateTemplate().save(module);
		
	}

	public void deleteModule(int moduleId) {
		Module module = (Module)this.getHibernateTemplate().load(Module.class, moduleId);
		
		if(module.getChildren().size() > 0) {
			throw new RuntimeException("存在子模块，不允许删除");
		}
		this.getHibernateTemplate().delete(module);

	}

	public Module findModule(int moduleId) {
		return (Module)this.getHibernateTemplate().load(Module.class, moduleId);
	}

	public void modifyModule(Module module, int parentId) {
		if(parentId != 0) {
			module.setParent((Module)this.getHibernateTemplate().load(Module.class, parentId));
		}
		
		this.getHibernateTemplate().update(module);

	}

	public PagerModel searchModules(int parentId) {
		return this.searchPaginated("select m from Module m where " + (parentId ==0 ? "m.parent is null" : ("m.parent.id = " + parentId)));
	}

}
