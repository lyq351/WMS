package com.jk.wms.invoice.bill.web;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.jk.wms.invoice.bill.business.ebi.BillEbi;
import com.jk.wms.invoice.bill.vo.BillQueryModel;
import com.jk.wms.invoice.orderdetail.vo.OrderDetailModel;
import com.jk.wms.invoice.supplier.business.ebi.SupplierEbi;
import com.jk.wms.invoice.supplier.vo.SupplierModel;
import com.jk.wms.util.base.BaseAction;

public class BillAction extends BaseAction{
	public BillQueryModel bqm = new BillQueryModel();

	private BillEbi billEbi;
	private SupplierEbi supplierEbi;
	
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}
	
	public String buyBillList(){
		
		//加载所有供应商信息
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		
		List<Object[]>  billList = billEbi.getBuyBill(bqm);
	
		put("billList",billList);
		return "buyBillList";
	}
	
	public void pieBill() throws Exception{
		HttpServletResponse response  = getResponse();
		OutputStream os = response.getOutputStream();
		//将jfreechart生成的图片信息写入到os中就行了
		billEbi.writeJFreeChartToOs(os,bqm);
	}
	
	
	private InputStream downloadExcelStreamn;
	public InputStream getDownloadExcelStreamn() {
		return downloadExcelStreamn;
	}
	private String xlsName;
	public String getXlsName() throws UnsupportedEncodingException {
		//字符级要进行过滤
		//xlsName->byte[]->string
		return new String(xlsName.getBytes("utf-8"),"iso8859-1");
	}
	//下载excel报表
	public String downloadExcelBill() throws Exception{
		//将要下载的内容写入downloadExcelStreamn中
		xlsName = "采购报表.xls";
		downloadExcelStreamn = billEbi.getWriteExcelStream(bqm);
		return "downloadExcelBill";
	}
	
	
	//----AJAX----------------------------------
	private List<OrderDetailModel> odmList;
	public List<OrderDetailModel> getOdmList() {
		return odmList;
	}
	public String ajaxGetBuyBillDetail(){
		//根据条件获取明细信息，传递到页面，通过json格式，页面获取后展示
		//所有的条件都封装在了bqm对象中
		odmList = billEbi.getBuyBillDetail(bqm);
		return "ajaxGetBuyBillDetail";
	}

	

}
