package com.jk.wms.auth.dep.web;

import java.util.List;

import com.jk.wms.auth.dep.business.ebi.DepEbi;
import com.jk.wms.auth.dep.vo.DepModel;
import com.jk.wms.auth.dep.vo.DepQueryModel;
import com.jk.wms.util.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class DepAction extends BaseAction {

	public DepModel dm = new DepModel(); //封装数据的。
	//定义一个收集查询条件的数据对象
	public DepQueryModel dqm = new DepQueryModel();
	
	//注入service
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	//跳转到部门的列表页面
	public String list(){
		setDataTotal(depEbi.getCount(dqm));
		List<DepModel> depList = depEbi.getAll(dqm,pageNum,pageCount);
		put("depList", depList);
		return LIST;
	}
	
	//跳转到新建页面，修改功能，需要带数据到添加页面
	public String input(){
		if(dm.getUuid()!=null){
			dm = depEbi.get(dm.getUuid());	
		}
		return INPUT;
	}
	
	//添加保存部门信息跳转
	public String save(){
		//根据页面传递的参数判断当前操作是添加还是修改，依据是否提供dm.uuid。
		if(dm.getUuid()==null){
			//添加功能
			//将收集的值传递到业务层，完成保存功能。
			depEbi.save(dm);
		}else{
			//修改功能
			depEbi.update(dm);	
		}	
		return TO_LIST;
	}

	
	//删除功能
	public String delete(){
		depEbi.delete(dm);
		return TO_LIST;
	}
	
	//查询列表功能，按条件查询。
		/*public String queryList(){
			//根据查询条件获取数据(查询条件封装在dqm对象中)
			List<DepModel> depList = depEbi.getAll(dqm);
	        //放入指定的范围
			ActionContext.getContext().put("depList", depList);
			//跳转页面
			return "list";
		}*/
	
	//修改功能，需要带数据到添加页面
/*	public String input2(){
		//查询数据，进行展示
		//获取指定被修改的信息，放到指定的范围。 可以用put放。  可以直接用dm，页面可以回写。struts2标签
		//后台拿到dm.uuid值
		dm = depEbi.get(dm.getUuid());
		
		return "input";
	}*/
	
	//修改功能
	/*public String update(){
		depEbi.update(dm);
		return "toList";
	}*/
}
