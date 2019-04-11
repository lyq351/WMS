package com.jk.wms.util.base;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jk.wms.auth.emp.vo.EmpModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction  extends ActionSupport{
	//跳转页面的常量
	public static final String LIST = "list";
	public static final String TO_LIST = "toList";
	public static final String INPUT = "input";
	
	//分页使用的常量
	public Integer pageNum = 1;
	public Integer pageCount = 10;
	public Integer maxPageNum;
	public Integer dataTotal;
	
	public String getActionName(){
		//动态
		//DepAction ->dep
		//EmpAction ->emp
		String actionName = this.getClass().getSimpleName();
		//DepAction ->Dep
		String temp = actionName.substring(0, actionName.length()-6);
		//Dep ->dep		OrderDetailAction   ->orderDetail  orderdetail
		return temp.substring(0,1).toLowerCase()+temp.substring(1);
	}
	
	protected void setDataTotal(int dataTotal){
		this.dataTotal = dataTotal ;
		maxPageNum = (dataTotal + pageCount -1) / pageCount;
	}
/*	//查询数据总条目数
	dataTotal = depEbi.getCount(dqm);
	//获取最大页码值：使用count与pageCount计算得到最大页码
	maxPageNum = (dataTotal + pageCount-1)/pageCount;
	//加载所有部门的信息，放到指定的范围，页面从指定的范围取出，展示。
	List<DepModel> depList = depEbi.getAll(dqm,pageNum,pageCount); 
	//指定范围，request(选用)，session，application。尽量选用最小的。
	ActionContext.getContext().put("depList", depList);
	return "list";
	*/
	
	//存放request范围数据
	protected void put(String name,Object obj){
		ActionContext.getContext().put(name,obj);
	}
	
	protected Object get(String name){
		return ActionContext.getContext().get(name);
	}
	//存放session范围数据
	protected void putSession(String name,Object obj){
		ActionContext.getContext().getSession().put(name,obj);
	}
	
	protected Object getSession(String name){
		return ActionContext.getContext().getSession().get(name);
	}
	
	protected EmpModel getLogin(){
		return (EmpModel) getSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
	}
	
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
