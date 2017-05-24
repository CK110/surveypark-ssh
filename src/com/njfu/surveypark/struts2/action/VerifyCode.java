package com.njfu.surveypark.struts2.action;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.util.RandomNumUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class VerifyCode extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -1801436791845429037L;
	//Struts2��Map���͵�session
	private Map<String, Object> session;
	private ByteArrayInputStream inputStream;   
	
	public String execute() throws Exception{   
		RandomNumUtil rdnu=RandomNumUtil.Instance();
		this.setInputStream(rdnu.getImage());//ȡ�ô�������ַ�����ͼƬ   
		session.put("random", rdnu.getString());//ȡ������ַ�������HttpSession
		 return SUCCESS;   
		}  
	
	public void setInputStream(ByteArrayInputStream inputStream) {   
		this.inputStream = inputStream;   
	}   
	public ByteArrayInputStream getInputStream() {   
		return inputStream;   
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}  
	
}



