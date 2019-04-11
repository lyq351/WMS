package com.jk.wms.auth.emp.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jk.wms.auth.dep.business.ebi.DepEbi;
import com.jk.wms.auth.dep.vo.DepModel;
import com.jk.wms.auth.emp.business.ebi.EmpEbi;
import com.jk.wms.auth.emp.vo.EmpModel;
import com.jk.wms.auth.emp.vo.EmpQueryModel;
import com.jk.wms.auth.res.business.ebi.ResEbi;
import com.jk.wms.auth.res.vo.ResModel;
import com.jk.wms.auth.role.business.ebi.RoleEbi;
import com.jk.wms.auth.role.vo.RoleModel;
import com.jk.wms.util.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;


public class EmpAction extends BaseAction{
	public EmpModel em = new EmpModel();
	public EmpQueryModel eqm = new EmpQueryModel();
	
	private String vcode;
	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	private EmpEbi empEbi;
	private DepEbi depEbi;
	private RoleEbi roleEbi;
	private ResEbi resEbi;
	
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	//列表
	public String list(){
		//加载所有部门的信息数据
		List<DepModel> depList = depEbi.getAll();
		put("depList",depList);
		//加载所有员工的信息数据，并分页。
		setDataTotal(empEbi.getCount(eqm));
		List<EmpModel> empList = empEbi.getAll(eqm,pageNum,pageCount);
		put("empList", empList);
		return LIST;
	}
	

	//到添加
	public String input(){
		//加载所有角色信息数据
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList",roleList);
		
		//加载所有部门信息数据
		List<DepModel> depList = depEbi.getAll();
		put("depList",depList);
		if(em.getUuid()!=null){
			em = empEbi.get(em.getUuid());
			//此时roleUuids中无数据，必须对其进行初始化才可以进行数据回显
			roleUuids = new Long[em.getRoles().size()];
			//set-->array
			int i =0;
			for(RoleModel temp : em.getRoles()){
				roleUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}

	//如何接收页面传递的多个参数使用相同的name值。 request.getparameters();
	public Long[] roleUuids;
	
	//添加
	public String save(){
		if(em.getUuid() == null){
			empEbi.save(em,roleUuids);
		}else{
			empEbi.update(em,roleUuids);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		empEbi.delete(em);
		return TO_LIST;
	}
	
	
	

	//登录
	public String login(){
		//获取IP方法
		HttpServletRequest request = getRequest();
		String loginIp = request.getHeader("x-forwarded-for"); 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if(loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) { 
			loginIp = request.getRemoteAddr(); 
		}
			
		String realVocde = (String)getSession("vcode");
		  
		
		
		EmpModel loginEm = empEbi.login(em.getUserName(),em.getPwd(),loginIp);
		if(loginEm == null || !realVocde.equalsIgnoreCase(vcode)){
			this.addActionError("对不起，用户名密码或验证码错误！");
			return "loginFail";
		}else{
			//加载当前登录人对应的所有可操作资源信息
			List<ResModel> resList = resEbi.getAllByEmp(loginEm.getUuid());
			StringBuilder sbf = new StringBuilder();
			for(ResModel rm : resList){
				sbf.append(rm.getUrl());
				sbf.append(",");
			}
			loginEm.setResAll(sbf.toString());
			putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, loginEm);
			return "loginSuccess";
		}
	}
	
	//登出/注销
	public String logout(){
		//1.获得session.removeAtrribute("name");
		//2.所谓登录失败指loginEm == null,setAttribute("name",null)
		putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, null);
		return "noLogin";
	}
	
	//跳转到修改密码
	public String toChangePwd(){
		return "toChangePwd";
	}
	
	public String newPwd;
	//修改密码
	public String changePwd(){
		boolean flag = empEbi.changePwd(getLogin().getUserName(),em.getPwd(),newPwd);
		if(flag){
			//修改成功
			//重新登录
			putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, null);
			return "noLogin";
		}else{
			//提示用户当前信息输入有误
			//信息：自己处理
			return "toChangePwd";
		}
	}
	
	
	
	
}
