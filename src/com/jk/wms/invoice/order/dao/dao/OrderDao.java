package com.jk.wms.invoice.order.dao.dao;

import java.util.List;

import com.jk.wms.invoice.order.vo.OrderModel;
import com.jk.wms.invoice.order.vo.OrderQueryModel;
import com.jk.wms.util.base.BaseDao;

public interface OrderDao extends BaseDao<OrderModel> {

	public Integer getCountOrderTypes(OrderQueryModel oqm, Integer[] buyCheckOrderTypes);

	public List<OrderModel> getAllOrderTypes(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount, Integer[] buyCheckOrderTypes);

}
