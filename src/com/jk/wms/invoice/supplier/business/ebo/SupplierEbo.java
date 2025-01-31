package com.jk.wms.invoice.supplier.business.ebo;

import java.io.Serializable;
import java.util.List;

import com.jk.wms.invoice.supplier.business.ebi.SupplierEbi;
import com.jk.wms.invoice.supplier.dao.dao.SupplierDao;
import com.jk.wms.invoice.supplier.vo.SupplierModel;
import com.jk.wms.util.base.BaseQueryModel;

public class SupplierEbo implements SupplierEbi{
	private SupplierDao supplierDao;
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public void save(SupplierModel sm) {
		supplierDao.save(sm);
	}

	public void update(SupplierModel sm) {
		supplierDao.update(sm);
	}

	public void delete(SupplierModel sm) {
		supplierDao.delete(sm);
	}

	public SupplierModel get(Serializable uuid) {
		return supplierDao.get(uuid);
	}

	public List<SupplierModel> getAll() {
		return supplierDao.getAll();
	}

	public List<SupplierModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return supplierDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return supplierDao.getCount(qm);
	}

	public List<SupplierModel> getAllUnion() {
		return supplierDao.getAllUnion();
	}

	public List<SupplierModel> getAllUnionTwo() {
		return supplierDao.getAllUnionTwo();
	}

}
