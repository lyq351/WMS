package com.jk.wms.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.auth.menu.vo.MenuModel;
import com.jk.wms.util.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel>{
    /**
     * 获取所有的系统菜单和一级菜单
     * @return
     */
	public List<MenuModel> getAllOneLevel();

	public void save(MenuModel mm, Long[] roleUuids);

	public void update(MenuModel mm, Long[] roleUuids);

	/**
	 * 获取员工可操作的所有以及菜单。
	 * @param uuid 员工uuid
	 * @return
	 */
	public List<MenuModel> getAllOneLevelByEmp(Long uuid);

	/**
	 * 获取指定员工对应的指定一级菜单可操作的二级菜单
	 * @param uuid  员工uuid
	 * @param puuid 一级菜单uuid
	 * @return
	 */
	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid);

}
