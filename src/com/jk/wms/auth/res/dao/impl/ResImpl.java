package com.jk.wms.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jk.wms.auth.res.dao.dao.ResDao;
import com.jk.wms.auth.res.vo.ResModel;
import com.jk.wms.auth.res.vo.ResQueryModel;
import com.jk.wms.util.base.BaseImpl;
import com.jk.wms.util.base.BaseQueryModel;

public class ResImpl extends BaseImpl<ResModel> implements ResDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		ResQueryModel rqm = (ResQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	public List<ResModel> getAllByEmpUuid(Long uuid) {
		String hql ="select res from EmpModel em join em.roles rm join rm.reses res where em.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

}
