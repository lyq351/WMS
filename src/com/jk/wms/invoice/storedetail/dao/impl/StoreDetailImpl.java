package com.jk.wms.invoice.storedetail.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jk.wms.invoice.storedetail.dao.dao.StoreDetailDao;
import com.jk.wms.invoice.storedetail.vo.StoreDetailModel;
import com.jk.wms.invoice.storedetail.vo.StoreDetailQueryModel;
import com.jk.wms.util.base.BaseImpl;
import com.jk.wms.util.base.BaseQueryModel;

public class StoreDetailImpl extends BaseImpl<StoreDetailModel> implements StoreDetailDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		StoreDetailQueryModel sqm = (StoreDetailQueryModel)qm;
		if(sqm.getSm()!=null && 
				sqm.getSm().getName()!=null && 
				sqm.getSm().getName().trim().length()>0){
				dc.createAlias("sm", "c1");
				dc.add(Restrictions.like("c1.name", "%"+sqm.getSm().getName().trim()+"%"));
			}
		if(sqm.getGm()!=null && 
				sqm.getGm().getName()!=null && 
				sqm.getGm().getName().trim().length()>0){
				dc.createAlias("gm", "c1");
				dc.add(Restrictions.like("c1.name", "%"+sqm.getGm().getName().trim()+"%"));
			}
		// TODO 添加自定义查询条件
	}

	public StoreDetailModel getBySmAndGm(Long storeUuid, Long uuid) {
		String hql ="from StoreDetailModel where sm.uuid = ? and gm.uuid= ?";
		List<StoreDetailModel> temp = this.getHibernateTemplate().find(hql,storeUuid,uuid);
		return temp.size()>0 ? temp.get(0):null;
	}

}
