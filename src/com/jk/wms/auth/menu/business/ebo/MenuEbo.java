package com.jk.wms.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jk.wms.auth.menu.business.ebi.MenuEbi;
import com.jk.wms.auth.menu.dao.dao.MenuDao;
import com.jk.wms.auth.menu.vo.MenuModel;
import com.jk.wms.auth.role.vo.RoleModel;
import com.jk.wms.util.base.BaseQueryModel;

public class MenuEbo implements MenuEbi{
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
    //废弃
	public void save(MenuModel mm) {
		menuDao.save(mm);
	}
    //废弃
	public void update(MenuModel mm) {
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setName(mm.getName());
		temp.setUrl(mm.getUrl());
		//menuDao.update(mm);
	}

	public void delete(MenuModel mm) {
		//级联删除前加载关系??
		MenuModel temp = menuDao.get(mm.getUuid());
		//temp对象此时就具有了延迟加载功能，可以随时加载关系
		menuDao.delete(temp);
	}

	public MenuModel get(Serializable uuid) {
		return menuDao.get(uuid);
	}

	public List<MenuModel> getAll() {
		return menuDao.getAll();
	}

	public List<MenuModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return menuDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return menuDao.getCount(qm);
	}

	public List<MenuModel> getAllOneLevel() {
		return menuDao.getByPuuidIsOneOrZero();
	}

	public void save(MenuModel mm, Long[] roleUuids) {
		//array->set->mm
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long uuid:roleUuids){
			RoleModel temp = new RoleModel();
			temp.setUuid(uuid);
			roles.add(temp);
		}
		mm.setRoles(roles);
		menuDao.save(mm);        		
	}

	public void update(MenuModel mm, Long[] roleUuids) {
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setName(mm.getName());
		temp.setUrl(mm.getUrl());
		
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long uuid:roleUuids){
			RoleModel temp2 = new RoleModel();
			temp2.setUuid(uuid);
			roles.add(temp2);
		}
		temp.setRoles(roles);		
	}
	public List<MenuModel> getAllOneLevelByEmp(Long uuid) {
		return menuDao.getAllOneLevelByEmpUuid(uuid);
	}
	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid) {
		return menuDao.getByEmpUuidAndPuuid(uuid,puuid);
	}

}
