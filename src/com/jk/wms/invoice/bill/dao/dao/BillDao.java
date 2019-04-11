package com.jk.wms.invoice.bill.dao.dao;

import java.util.List;

import com.jk.wms.invoice.bill.vo.BillQueryModel;
import com.jk.wms.invoice.orderdetail.vo.OrderDetailModel;

public interface BillDao {

	public List<Object[]> getBuyBill(BillQueryModel bqm);
	
	public List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm);

}
