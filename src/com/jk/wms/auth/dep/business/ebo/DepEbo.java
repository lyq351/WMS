package com.jk.wms.auth.dep.business.ebo;

import java.io.Serializable;
import java.util.List;

import com.jk.wms.auth.dep.business.ebi.DepEbi;
import com.jk.wms.auth.dep.dao.dao.DepDao;
import com.jk.wms.auth.dep.vo.DepModel;
import com.jk.wms.util.base.BaseQueryModel;

public class DepEbo implements DepEbi {

	//注入dao
	private DepDao depDao;
	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}
	
	public void save(DepModel dm) {
      //调用dao方法,持久化数据
		depDao.save(dm);
		
	}

	public List<DepModel> getAll() {
		return depDao.getAll();
	}

	public DepModel get(Serializable uuid) {
		return depDao.get(uuid);
	}

	public void update(DepModel dm) {
        depDao.update(dm);		
	}

	public void delete(DepModel dm) {
       depDao.delete(dm);		
	}

	/*public List<DepModel> getAll(BaseQueryModel qm) {
		return depDao.getAll(qm);
	}*/

	public List<DepModel> getAll(BaseQueryModel qm, Integer pageNum,
			Integer pageCount) {
		return depDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return depDao.getCount(qm);
	}
	
}
