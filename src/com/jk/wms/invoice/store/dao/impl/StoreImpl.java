package com.jk.wms.invoice.store.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jk.wms.invoice.store.dao.dao.StoreDao;
import com.jk.wms.invoice.store.vo.StoreModel;
import com.jk.wms.invoice.store.vo.StoreQueryModel;
import com.jk.wms.util.base.BaseImpl;
import com.jk.wms.util.base.BaseQueryModel;

public class StoreImpl extends BaseImpl<StoreModel> implements StoreDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		StoreQueryModel sqm = (StoreQueryModel)qm;
		if(sqm.getName()!=null && sqm.getName().trim().length() > 0 ){
			dc.add(Restrictions.like("name", "%"+sqm.getName().trim()+"%"));
		}	
	}

}
