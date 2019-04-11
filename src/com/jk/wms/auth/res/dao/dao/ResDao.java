package com.jk.wms.auth.res.dao.dao;

import java.util.List;

import com.jk.wms.auth.res.vo.ResModel;
import com.jk.wms.util.base.BaseDao;

public interface ResDao extends BaseDao<ResModel> {

	public List<ResModel> getAllByEmpUuid(Long uuid);

}
