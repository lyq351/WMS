package com.jk.wms.invoice.goodstype.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jk.wms.invoice.goodstype.dao.dao.GoodsTypeDao;
import com.jk.wms.invoice.goodstype.vo.GoodsTypeModel;
import com.jk.wms.invoice.goodstype.vo.GoodsTypeQueryModel;
import com.jk.wms.util.base.BaseImpl;
import com.jk.wms.util.base.BaseQueryModel;

public class GoodsTypeImpl extends BaseImpl<GoodsTypeModel> implements GoodsTypeDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		GoodsTypeQueryModel gqm = (GoodsTypeQueryModel)qm;
		if(gqm.getName()!=null && gqm.getName().trim().length() > 0 ){
			dc.add(Restrictions.like("name", "%"+gqm.getName().trim()+"%"));
		}
	}

	public List<GoodsTypeModel> getAllBySmUuid(Long uuid) {
		String hql = "from GoodsTypeModel where sm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

	public List<GoodsTypeModel> getAllUnionBySmUuid(Long uuid) {
		//gtm->gm(没有关系)
		//gm->gtm
		String hql = "select distinct gt from GoodsModel gm join gm.gtm gt where gt.sm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}

}
