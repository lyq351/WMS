package com.jk.wms.invoice.order.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jk.wms.invoice.order.dao.dao.OrderDao;
import com.jk.wms.invoice.order.vo.OrderModel;
import com.jk.wms.invoice.order.vo.OrderQueryModel;
import com.jk.wms.util.base.BaseImpl;
import com.jk.wms.util.base.BaseQueryModel;

public class OrderImpl extends BaseImpl<OrderModel> implements OrderDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		OrderQueryModel oqm = (OrderQueryModel)qm;
		if(oqm.getOrderType()!=null && oqm.getOrderType()!= -1){
			dc.add(Restrictions.eq("orderType", oqm.getOrderType()));
		}
		if(oqm.getCreater()!=null && 
			oqm.getCreater().getName()!=null && 
			oqm.getCreater().getName().trim().length()>0){
			dc.createAlias("creater", "c1");
			dc.add(Restrictions.like("c1.name", "%"+oqm.getCreater().getName().trim()+"%"));
		}
		if(oqm.getChecker()!=null && 
				oqm.getChecker().getName()!=null && 
				oqm.getChecker().getName().trim().length()>0){
			dc.createAlias("checker", "c2");
			dc.add(Restrictions.like("c2.name", "%"+oqm.getChecker().getName().trim()+"%"));
		}
		if(oqm.getCompleter()!=null && 
				oqm.getCompleter().getName()!=null && 
				oqm.getCompleter().getName().trim().length()>0){
			dc.createAlias("completer", "c3");
			dc.add(Restrictions.like("c3.name", "%"+oqm.getCompleter().getName().trim()+"%"));
		}
		if(oqm.getCompleter()!=null && oqm.getCompleter().getUuid()!=null && oqm.getCompleter().getUuid()!=-1){
			dc.add(Restrictions.eq("completer", oqm.getCompleter()));
		}
		dc.createAlias("sm", "s");
		if(oqm.getSm()!=null && oqm.getSm().getUuid()!=null && oqm.getSm().getUuid()!= -1){
			dc.add(Restrictions.eq("s.uuid", oqm.getSm()));
		}
		if(oqm.getSm()!=null && oqm.getSm().getNeeds()!=null && oqm.getSm().getNeeds()!= -1){
			dc.add(Restrictions.eq("s.needs", oqm.getSm().getNeeds()));
		}
		if(oqm.getType()!=null && oqm.getType()!=-1){
			dc.add(Restrictions.eq("type", oqm.getType()));
		}
		// TODO 添加自定义查询条件
	}
	
	private void doQbc2(DetachedCriteria dc,BaseQueryModel qm,Integer[] orderTypes){
		dc.add(Restrictions.in("orderType", orderTypes));
		doQbc(dc,qm);
	}
	
	private void doQbc3(DetachedCriteria dc,BaseQueryModel qm,Integer[] orderTypes){
		dc.add(Restrictions.in("type", orderTypes));
		doQbc(dc,qm);
	}	

	public Integer getCountOrderTypes(OrderQueryModel oqm,
			Integer[] orderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		dc.setProjection(Projections.rowCount());
		doQbc2(dc, oqm, orderTypes);
		List<Long> count = this.getHibernateTemplate().findByCriteria(dc);
		return count.get(0).intValue();
	}

	public List<OrderModel> getAllOrderTypes(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount, Integer[] orderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		//orderType在指定形参orderTypes范围内
		doQbc2(dc,oqm,orderTypes);
		return this.getHibernateTemplate().findByCriteria(dc,(pageNum-1)*pageCount,pageCount);
	}

}
