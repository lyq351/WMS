package com.jk.wms.invoice.bill.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jk.wms.invoice.bill.dao.dao.BillDao;
import com.jk.wms.invoice.bill.vo.BillQueryModel;
import com.jk.wms.invoice.orderdetail.vo.OrderDetailModel;
import com.jk.wms.util.base.BaseQueryModel;

public class BillImpl extends HibernateDaoSupport implements BillDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		BillQueryModel bqm = (BillQueryModel)qm;
		// TODO 添加自定义查询条件
	}

	public List<Object[]> getBuyBill(BillQueryModel bqm) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		
		//设置一条查询的查询结果内容为多个内容
		ProjectionList plist = Projections.projectionList();
		//分组(HQL,SQL中使用分组采用group by来完成，QBC中分组是使用投影完成的)
		plist.add(Projections.groupProperty("gm"));
		//select的内容
		plist.add(Projections.sum("num"));
		dc.setProjection(plist);
		//条件
		dc.createAlias("om", "o");
		if(bqm.getType()!=null && bqm.getType()!=-1){
			dc.add(Restrictions.eq("o.type", bqm.getType()));
		}
		if(bqm.getSupplierUuid()!=null && bqm.getSupplierUuid()!= -1){
			dc.createAlias("o.sm", "s");
			dc.add(Restrictions.eq("s.uuid", bqm.getSupplierUuid()));
		}
			
		return this.getHibernateTemplate().findByCriteria(dc);
	}
	
	public List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderDetailModel.class);
		//条件
		dc.add(Restrictions.eq("gm.uuid", bqm.getGoodsUuid()));
		
		dc.createAlias("om", "o");
		if(bqm.getType()!=null && bqm.getType()!=-1){
			dc.add(Restrictions.eq("o.type", bqm.getType()));
		}
		if(bqm.getSupplierUuid()!=null && bqm.getSupplierUuid()!= -1){
			dc.createAlias("o.sm", "s");
			dc.add(Restrictions.eq("s.uuid", bqm.getSupplierUuid()));
		}
		return this.getHibernateTemplate().findByCriteria(dc);
	}

}
