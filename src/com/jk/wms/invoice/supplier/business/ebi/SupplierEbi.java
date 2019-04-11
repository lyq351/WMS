package com.jk.wms.invoice.supplier.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.invoice.supplier.vo.SupplierModel;
import com.jk.wms.util.base.BaseEbi;

@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel>{

	/**
	 * 获取具有商品类别信息的供应商信息
	 * @return
	 */
	public List<SupplierModel> getAllUnion();

	/**
	 * 获取商品的商品类别的供应商
	 * @return
	 */
	public List<SupplierModel> getAllUnionTwo();

}
