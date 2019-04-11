package com.jk.wms.invoice.goodstype.web;

import java.util.List;
import com.jk.wms.invoice.goodstype.business.ebi.GoodsTypeEbi;
import com.jk.wms.invoice.goodstype.vo.GoodsTypeModel;
import com.jk.wms.invoice.goodstype.vo.GoodsTypeQueryModel;
import com.jk.wms.invoice.supplier.business.ebi.SupplierEbi;
import com.jk.wms.invoice.supplier.vo.SupplierModel;
import com.jk.wms.util.base.BaseAction;

public class GoodsTypeAction extends BaseAction{
	public GoodsTypeModel gm = new GoodsTypeModel();
	public GoodsTypeQueryModel gqm = new GoodsTypeQueryModel();

	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	private GoodsTypeEbi goodsTypeEbi;
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	//列表
	public String list(){
		setDataTotal(goodsTypeEbi.getCount(gqm));
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(gqm,pageNum,pageCount);
		put("goodsTypeList", goodsTypeList);
		return LIST;
	}

	//到添加
	public String input(){
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		if(gm.getUuid()!=null){
			gm = goodsTypeEbi.get(gm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(gm.getUuid() == null){
			goodsTypeEbi.save(gm);
		}else{
			goodsTypeEbi.update(gm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		goodsTypeEbi.delete(gm);
		return TO_LIST;
	}
	
	//----AJAX-----------------------------------------------
	//----AJAX-----------------------------------------------
	//0.加入struts-json对应的jar包
	//1.设置struts返回result的type为json
	//2.设置对应action所在的package继承自json-default
	//3.将要返回的数据提供对应的get方法	
	
	private List<GoodsTypeModel> gtmList;
	public List<GoodsTypeModel> getGtmList() {
		return gtmList;
	}
	
	private boolean flag = true;
	public boolean isFlag() {
		return flag;
	}
	//ajax获取供应商对应的类别信息
	public String ajaxGetBySm(){
		//根据供应商的uuid获取对应的类别信息
		gtmList = goodsTypeEbi.getAllBySm(gm.getSm().getUuid());
		flag = gtmList.size() > 0;
		//如何将数据传递出去（JSON格式）
		//使用json工具类JSONArray
		return "ajaxGetBySm";
	}

}
