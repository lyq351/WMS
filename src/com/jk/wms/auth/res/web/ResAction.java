package com.jk.wms.auth.res.web;

import java.util.List;

import com.jk.wms.auth.res.business.ebi.ResEbi;
import com.jk.wms.auth.res.vo.ResModel;
import com.jk.wms.auth.res.vo.ResQueryModel;
import com.jk.wms.util.base.BaseAction;

public class ResAction extends BaseAction{
	public ResModel rm = new ResModel();
	public ResQueryModel rqm = new ResQueryModel();

	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	//列表
	public String list(){
		setDataTotal(resEbi.getCount(rqm));
		List<ResModel> resList = resEbi.getAll(rqm,pageNum,pageCount);
		put("resList", resList);
		return LIST;
	}

	//到添加
	public String input(){
		if(rm.getUuid()!=null){
			rm = resEbi.get(rm.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(rm.getUuid() == null){
			resEbi.save(rm);
		}else{
			resEbi.update(rm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		resEbi.delete(rm);
		return TO_LIST;
	}

}
