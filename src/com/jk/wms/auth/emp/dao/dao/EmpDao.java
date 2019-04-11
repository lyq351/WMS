package com.jk.wms.auth.emp.dao.dao;

import java.util.List;

import com.jk.wms.auth.emp.vo.EmpModel;
import com.jk.wms.util.base.BaseDao;



public interface EmpDao extends BaseDao<EmpModel> {
	public EmpModel getByUserNameAndPwd(String userName, String pwd);

	public boolean updatePwdByUserNameAndPwd(String userName, String pwd,
			String newPwd);

	public List<EmpModel> getAllByDepUuid(Long uuid);
}
