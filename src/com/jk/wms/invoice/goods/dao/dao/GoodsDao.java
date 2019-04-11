package com.jk.wms.invoice.goods.dao.dao;

import java.util.List;

import com.jk.wms.invoice.goods.vo.GoodsModel;
import com.jk.wms.util.base.BaseDao;

public interface GoodsDao extends BaseDao<GoodsModel> {

	public List<GoodsModel> getAllByGtmUuid(Long uuid);

}
