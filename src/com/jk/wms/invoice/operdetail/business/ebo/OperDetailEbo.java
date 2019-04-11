package com.jk.wms.invoice.operdetail.business.ebo;

import java.io.Serializable;
import java.util.List;

import com.jk.wms.invoice.operdetail.business.ebi.OperDetailEbi;
import com.jk.wms.invoice.operdetail.dao.dao.OperDetailDao;
import com.jk.wms.invoice.operdetail.vo.OperDetailModel;
import com.jk.wms.util.base.BaseQueryModel;

public class OperDetailEbo implements OperDetailEbi{
	private OperDetailDao operDetailDao;
	public void setOperDetailDao(OperDetailDao operDetailDao) {
		this.operDetailDao = operDetailDao;
	}

	public void save(OperDetailModel om) {
		operDetailDao.save(om);
	}

	public void update(OperDetailModel om) {
		operDetailDao.update(om);
	}

	public void delete(OperDetailModel om) {
		operDetailDao.delete(om);
	}

	public OperDetailModel get(Serializable uuid) {
		return operDetailDao.get(uuid);
	}

	public List<OperDetailModel> getAll() {
		return operDetailDao.getAll();
	}

	public List<OperDetailModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return operDetailDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return operDetailDao.getCount(qm);
	}

}
