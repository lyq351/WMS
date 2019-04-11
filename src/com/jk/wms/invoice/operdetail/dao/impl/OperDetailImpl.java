package com.jk.wms.invoice.operdetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jk.wms.invoice.operdetail.dao.dao.OperDetailDao;
import com.jk.wms.invoice.operdetail.vo.OperDetailModel;
import com.jk.wms.invoice.operdetail.vo.OperDetailQueryModel;
import com.jk.wms.util.base.BaseImpl;
import com.jk.wms.util.base.BaseQueryModel;

public class OperDetailImpl extends BaseImpl<OperDetailModel> implements OperDetailDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		OperDetailQueryModel oqm = (OperDetailQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}
