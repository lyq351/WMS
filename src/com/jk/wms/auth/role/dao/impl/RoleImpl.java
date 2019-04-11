package com.jk.wms.auth.role.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jk.wms.auth.role.dao.dao.RoleDao;
import com.jk.wms.auth.role.vo.RoleModel;
import com.jk.wms.auth.role.vo.RoleQueryModel;
import com.jk.wms.util.base.BaseImpl;
import com.jk.wms.util.base.BaseQueryModel;

public class RoleImpl extends BaseImpl<RoleModel> implements RoleDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		RoleQueryModel rqm = (RoleQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}
