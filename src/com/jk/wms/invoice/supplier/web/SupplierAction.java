package com.jk.wms.invoice.supplier.web;

import java.util.List;

import com.jk.wms.invoice.supplier.business.ebi.SupplierEbi;
import com.jk.wms.invoice.supplier.vo.SupplierModel;
import com.jk.wms.invoice.supplier.vo.SupplierQueryModel;
import com.jk.wms.util.base.BaseAction;

public class SupplierAction extends BaseAction{
	public SupplierModel sm = new SupplierModel();
	public SupplierQueryModel sqm = new SupplierQueryModel();

	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	//列表
	public String list(){
		setDataTotal(supplierEbi.getCount(sqm));
		List<SupplierModel> supplierList = supplierEbi.getAll(sqm,pageNum,pageCount);
		put("supplierList", supplierList);
		return LIST;
	}

	//到添加
	public String input(){
		if(sm.getUuid()!=null){
			sm = supplierEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(sm.getUuid() == null){
			supplierEbi.save(sm);
		}else{
			supplierEbi.update(sm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		supplierEbi.delete(sm);
		return TO_LIST;
	}

}
