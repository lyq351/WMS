package com.jk.wms.auth.menu.dao.dao;

import java.util.List;

import com.jk.wms.auth.menu.vo.MenuModel;
import com.jk.wms.util.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel> {
    /**
     * 获取所有的一级菜单和系统菜单
     * @return
     */
	public List<MenuModel> getByPuuidIsOneOrZero();

	public List<MenuModel> getAllOneLevelByEmpUuid(Long uuid);

	public List<MenuModel> getByEmpUuidAndPuuid(Long uuid, Long puuid);

}
