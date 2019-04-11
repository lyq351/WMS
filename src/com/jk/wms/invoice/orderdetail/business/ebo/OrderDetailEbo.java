package com.jk.wms.invoice.orderdetail.business.ebo;

import java.io.Serializable;
import java.util.List;

import com.jk.wms.invoice.orderdetail.business.ebi.OrderDetailEbi;
import com.jk.wms.invoice.orderdetail.dao.dao.OrderDetailDao;
import com.jk.wms.invoice.orderdetail.vo.OrderDetailModel;
import com.jk.wms.util.base.BaseQueryModel;

public class OrderDetailEbo implements OrderDetailEbi{
	private OrderDetailDao orderDetailDao;
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void save(OrderDetailModel om) {
		orderDetailDao.save(om);
	}

	public void update(OrderDetailModel om) {
		orderDetailDao.update(om);
	}

	public void delete(OrderDetailModel om) {
		orderDetailDao.delete(om);
	}

	public OrderDetailModel get(Serializable uuid) {
		return orderDetailDao.get(uuid);
	}

	public List<OrderDetailModel> getAll() {
		return orderDetailDao.getAll();
	}

	public List<OrderDetailModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return orderDetailDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return orderDetailDao.getCount(qm);
	}

}
