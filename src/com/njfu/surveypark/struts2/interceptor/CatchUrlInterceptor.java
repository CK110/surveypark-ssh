package com.njfu.surveypark.struts2.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.njfu.surveypark.service.RightService;
import com.njfu.surveypark.util.ValidateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * ����URL������
 * @author CK
 * 2015��3��25������9:49:51
 */
public class CatchUrlInterceptor implements Interceptor {
	
	private static final long serialVersionUID = 4182438711506706447L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy proxy = invocation.getProxy();
		//���ֿռ�
		String ns = proxy.getNamespace();
		//actionName
		String actionName = proxy.getActionName();
		if(!ValidateUtil.isValid(ns)
				||ns.equals("/")){ 
			ns = "" ;
		}
		String url = ns + "/" + actionName ;
		
		//ȡ����applicationspring����.
		//ApplicationContext ac = (ApplicationContext) invocation.getInvocationContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//
		ServletContext sc = ServletActionContext.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		RightService rs = (RightService) ac.getBean("rightService");
		
		rs.appendRightByURL(url);
		return invocation.invoke();
	}

}
