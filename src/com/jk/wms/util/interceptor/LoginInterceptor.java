package com.jk.wms.util.interceptor;

import com.jk.wms.auth.emp.vo.EmpModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		//执行除了登入操作之前的所有做登入校验
		//获取本次登入的信息
		/*invocation.getAction();
		invocation.getProxy().getAction();
		invocation.getProxy().getActionName();
		invocation.getProxy().getMethod();*/
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName + "." + methodName;
		
		String operName = invocation.getProxy().getActionName();		//page_login
		if("page_login".equals(operName)){
			return invocation.invoke();
		}
		
		//判断是否是登入操作
		if("com.jk.wms.auth.emp.web.EmpAction.login".equals(allName)){
			return invocation.invoke();
		}
		//获取当前登入人的信息
		EmpModel loginEm =(EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
        //判断登陆是否为空
		if(loginEm == null){
			//跳转到登入页面
			return "noLogin";
		}
		//执行原始操作
		return invocation.invoke();
	}

}
