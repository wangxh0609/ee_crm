package com.hust.crm.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		
		
		Object obj=ActionContext.getContext().getSession().get("loginStaff");
		//没有登录
		if(obj==null){
			//友好信息
			//获得当前运行的action
			Object action=invocation.getAction();
			//运行时是否是ActionSupport
			if (action instanceof ActionSupport){
				ActionSupport actionSupport=(ActionSupport)action;
				actionSupport.addFieldError("", "请登录");
			}				
			return "login";
		}
		//登录 放行			
		return invocation.invoke();
	}

}
