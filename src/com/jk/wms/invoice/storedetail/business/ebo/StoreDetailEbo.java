package com.jk.wms.invoice.storedetail.business.ebo;

import java.io.Serializable;
import java.util.List;

import com.jk.wms.invoice.storedetail.business.ebi.StoreDetailEbi;
import com.jk.wms.invoice.storedetail.dao.dao.StoreDetailDao;
import com.jk.wms.invoice.storedetail.vo.StoreDetailModel;
import com.jk.wms.util.base.BaseQueryModel;

public class StoreDetailEbo implements StoreDetailEbi{
	private StoreDetailDao storeDetailDao;
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}

	public void save(StoreDetailModel sm) {
		storeDetailDao.save(sm);
	}

	public void update(StoreDetailModel sm) {
		storeDetailDao.update(sm);
	}

	public void delete(StoreDetailModel sm) {
		storeDetailDao.delete(sm);
	}

	public StoreDetailModel get(Serializable uuid) {
		return storeDetailDao.get(uuid);
	}

	public List<StoreDetailModel> getAll() {
		return storeDetailDao.getAll();
	}

	public List<StoreDetailModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return storeDetailDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return storeDetailDao.getCount(qm);
	}

}
