package com.jk.wms.invoice.supplier.dao.dao;

import java.util.List;

import com.jk.wms.invoice.supplier.vo.SupplierModel;
import com.jk.wms.util.base.BaseDao;

public interface SupplierDao extends BaseDao<SupplierModel> {

	public List<SupplierModel> getAllUnion();

	public List<SupplierModel> getAllUnionTwo();

}
