package com.jk.wms.invoice.goodstype.dao.dao;

import java.util.List;

import com.jk.wms.invoice.goodstype.vo.GoodsTypeModel;
import com.jk.wms.util.base.BaseDao;

public interface GoodsTypeDao extends BaseDao<GoodsTypeModel> {

	public List<GoodsTypeModel> getAllBySmUuid(Long uuid);

	public List<GoodsTypeModel> getAllUnionBySmUuid(Long uuid);

}
