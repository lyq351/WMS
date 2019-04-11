package com.jk.wms.invoice.goods.business.ebo;

import java.io.Serializable;
import java.util.List;

import com.jk.wms.invoice.goods.business.ebi.GoodsEbi;
import com.jk.wms.invoice.goods.dao.dao.GoodsDao;
import com.jk.wms.invoice.goods.vo.GoodsModel;
import com.jk.wms.util.base.BaseQueryModel;

public class GoodsEbo implements GoodsEbi{
	private GoodsDao goodsDao;
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void save(GoodsModel gm) {
		goodsDao.save(gm);
	}

	public void update(GoodsModel gm) {
		goodsDao.update(gm);
	}

	public void delete(GoodsModel gm) {
		goodsDao.delete(gm);
	}

	public GoodsModel get(Serializable uuid) {
		return goodsDao.get(uuid);
	}

	public List<GoodsModel> getAll() {
		return goodsDao.getAll();
	}

	public List<GoodsModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return goodsDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return goodsDao.getCount(qm);
	}

	public List<GoodsModel> getAllByGtm(Long uuid) {
		return goodsDao.getAllByGtmUuid(uuid);
	}

}
