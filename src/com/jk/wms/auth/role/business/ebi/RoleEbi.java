package com.jk.wms.auth.role.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.auth.role.vo.RoleModel;
import com.jk.wms.util.base.BaseEbi;

@Transactional
public interface RoleEbi extends BaseEbi<RoleModel>{
	/**
	 * 
	 * @param rm
	 * @param resUuids
	 * @param menuUuids
	 */
	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids);

	/**
	 * 
	 * @param rm
	 * @param resUuids
	 * @param menuUuids
	 */
	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids);

}
