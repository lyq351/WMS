package com.jk.wms.auth.role.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jk.wms.auth.menu.vo.MenuModel;
import com.jk.wms.auth.res.vo.ResModel;
import com.jk.wms.auth.role.business.ebi.RoleEbi;
import com.jk.wms.auth.role.dao.dao.RoleDao;
import com.jk.wms.auth.role.vo.RoleModel;
import com.jk.wms.util.base.BaseQueryModel;

public class RoleEbo implements RoleEbi{
	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
    //废弃
	public void save(RoleModel rm) {
		roleDao.save(rm);
	}
	//废弃
	public void update(RoleModel rm) {
		roleDao.update(rm);
	}

	public void delete(RoleModel rm) {
		roleDao.delete(rm);
	}

	public RoleModel get(Serializable uuid) {
		return roleDao.get(uuid);
	}

	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}

	public List<RoleModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return roleDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return roleDao.getCount(qm);
	}

	public List<RoleModel> getAll(BaseQueryModel qm) {
		return null;
	}
	
	public void save(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		//array->set
		Set<ResModel> reses = new HashSet<ResModel>();
		for(Long uuid:resUuids){
			ResModel temp = new ResModel();
			temp.setUuid(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for(Long uuid:menuUuids){
			MenuModel temp = new MenuModel();
			temp.setUuid(uuid);
			menus.add(temp);
		}
		rm.setMenus(menus);
		
		roleDao.save(rm);
	}
	public void update(RoleModel rm, Long[] resUuids, Long[] menuUuids) {
		Set<ResModel> reses = new HashSet<ResModel>();
		for(Long uuid:resUuids){
			ResModel temp = new ResModel();
			temp.setUuid(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for(Long uuid:menuUuids){
			MenuModel temp = new MenuModel();
			temp.setUuid(uuid);
			menus.add(temp);
		}
		rm.setMenus(menus);
		
		roleDao.update(rm);
	}
	
}
