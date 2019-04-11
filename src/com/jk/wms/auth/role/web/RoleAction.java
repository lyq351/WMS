package com.jk.wms.auth.role.web;

import java.util.List;

import com.jk.wms.auth.menu.business.ebi.MenuEbi;
import com.jk.wms.auth.menu.vo.MenuModel;
import com.jk.wms.auth.res.business.ebi.ResEbi;
import com.jk.wms.auth.res.vo.ResModel;
import com.jk.wms.auth.role.business.ebi.RoleEbi;
import com.jk.wms.auth.role.vo.RoleModel;
import com.jk.wms.auth.role.vo.RoleQueryModel;
import com.jk.wms.util.base.BaseAction;

public class RoleAction extends BaseAction{
	public RoleModel rm = new RoleModel();
	public RoleQueryModel rqm = new RoleQueryModel();
	
	private MenuEbi menuEbi;
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}
	private RoleEbi roleEbi;
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}
	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	//列表
	public String list(){
		setDataTotal(roleEbi.getCount(rqm));
		List<RoleModel> roleList = roleEbi.getAll(rqm,pageNum,pageCount);
		put("roleList", roleList);
		return LIST;
	}

	//到添加
	public String input(){
		//加载菜单信息列表
		List<MenuModel> menuList = menuEbi.getAll();
		put("menuList",menuList);
		//加载资源列表
		List<ResModel> resList = resEbi.getAll();
		put("resList",resList);
		if(rm.getUuid()!=null){
			rm = roleEbi.get(rm.getUuid());
			//对rese进行初始化,set->array
			resUuids = new Long[rm.getReses().size()];
            int i =0;
			for(ResModel temp : rm.getReses()){
				resUuids[i++] = temp.getUuid();
			}
			
			menuUuids = new Long[rm.getMenus().size()];
			i = 0;
			for(MenuModel temp : rm.getMenus()){
				menuUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}
    //资源uuid
	public Long[] resUuids;
	//菜单uuid
	public Long[] menuUuids;
	//添加
	public String save(){
		if(rm.getUuid() == null){
			roleEbi.save(rm,resUuids,menuUuids);
		}else{
			roleEbi.update(rm,resUuids,menuUuids);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		roleEbi.delete(rm);
		return TO_LIST;
	}

}
