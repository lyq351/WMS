package com.jk.wms.invoice.supplier.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jk.wms.invoice.supplier.dao.dao.SupplierDao;
import com.jk.wms.invoice.supplier.vo.SupplierModel;
import com.jk.wms.invoice.supplier.vo.SupplierQueryModel;
import com.jk.wms.util.base.BaseImpl;
import com.jk.wms.util.base.BaseQueryModel;

public class SupplierImpl extends BaseImpl<SupplierModel> implements SupplierDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		SupplierQueryModel sqm = (SupplierQueryModel)qm;
		if(sqm.getName()!=null && sqm.getName().trim().length() > 0 ){
			dc.add(Restrictions.like("name", "%"+sqm.getName().trim()+"%"));
		}
		
	}

	public List<SupplierModel> getAllUnion() {
		//需要查询供应商与类别信息由关联的数据
		String hql = "select distinct s from GoodsTypeModel gtm join gtm.sm s";
		return this.getHibernateTemplate().find(hql);
	}

	public List<SupplierModel> getAllUnionTwo() {
		//sm->gtm->gm(缺少关系)
		//gm->gtm->sm
		String hql = "select distinct s from GoodsModel gm join gm.gtm gt join gt.sm s";
		return this.getHibernateTemplate().find(hql);
	}

}
