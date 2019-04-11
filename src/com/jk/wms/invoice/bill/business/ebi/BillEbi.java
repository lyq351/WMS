package com.jk.wms.invoice.bill.business.ebi;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.invoice.bill.vo.BillQueryModel;
import com.jk.wms.invoice.orderdetail.vo.OrderDetailModel;

@Transactional
public interface BillEbi {

	public List<Object[]> getBuyBill(BillQueryModel bqm);
	
	public List<OrderDetailModel> getBuyBillDetail(BillQueryModel bqm);

	public void writeJFreeChartToOs(OutputStream os,BillQueryModel bqm) throws Exception;

	public InputStream getWriteExcelStream(BillQueryModel bqm) throws Exception;

}
