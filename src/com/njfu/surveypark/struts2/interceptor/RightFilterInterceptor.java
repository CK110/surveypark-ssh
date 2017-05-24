package com.njfu.surveypark.struts2.interceptor;

import org.apache.struts2.ServletActionContext;

import com.njfu.surveypark.util.ValidateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class RightFilterInterceptor implements Interceptor {

	private static final long serialVersionUID = 125051199562589045L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionProxy proxy = arg0.getProxy();
		String ns = proxy.getNamespace();
		String actionName = proxy.getActionName();

		if(ValidateUtil.hasRight(ns, actionName, ServletActionContext.getRequest())){
			return arg0.invoke();
		}
		return "login" ;
	}

}
