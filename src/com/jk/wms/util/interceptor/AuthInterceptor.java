package com.jk.wms.util.interceptor;


import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.jk.wms.auth.emp.vo.EmpModel;
import com.jk.wms.auth.res.business.ebi.ResEbi;
import com.jk.wms.auth.res.vo.ResModel;
import com.jk.wms.util.exception.AppException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//权限校验
public class AuthInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation invocation) throws Exception {
		//1.获取本次操作
		//2.判断本次操作是否是被拦截操作
		//3.从session中获取当前登录人信息
		//4.获取当前登录人可执行的所有操作（资源-角色-员工）
		//5.判断当前登录人对应的所有可操作资源中是否包含有本次操作
		
		String actionName  = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName+"."+methodName;
		
		String allRes = ServletActionContext.getServletContext().getAttribute("allRes").toString();
		if(!allRes.contains(allName)){
			return invocation.invoke();
		}
		
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		//对于登录用户对应的可操作资源在每次登录过程中，均保持不变
		//可以考虑进行一次性加载工作，放入指定范围后，每次使用直接获取
		//查询时机：登录时查询
		//范围：session
		//loginEm.getResAll即为当前登录人可进行的所有资源数据字符串
		if(loginEm.getResAll().contains(allName)){
			return invocation.invoke();
		}
		
		throw new AppException("对不起，请不要进行非法操作，权限不足！");
	}

}
