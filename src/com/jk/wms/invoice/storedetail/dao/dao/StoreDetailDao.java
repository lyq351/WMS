package com.jk.wms.invoice.storedetail.dao.dao;

import com.jk.wms.invoice.storedetail.vo.StoreDetailModel;
import com.jk.wms.util.base.BaseDao;

public interface StoreDetailDao extends BaseDao<StoreDetailModel> {

	public StoreDetailModel getBySmAndGm(Long storeUuid, Long uuid);

}
