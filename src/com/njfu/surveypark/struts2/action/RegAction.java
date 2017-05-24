package com.njfu.surveypark.struts2.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.User;
import com.njfu.surveypark.service.UserService;
import com.njfu.surveypark.util.DataUtil;

/**
 * ע��action
 */
@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User> implements SessionAware{

	private static final long serialVersionUID = 1841540181400834320L;
	
	//ע��userService
	@Resource
	private UserService userService ;
	private String email;
	private String password;
	private String confirmPassword;
	private String confirmPassword1;
	//���տͻ��˴�������֤��
	private String captcha;
	//Struts2��Map���͵�session
	private Map<String, Object> session;
	private int fieldError;
			
	/**
	 * ����ע��ҳ��
	 */
	@SkipValidation
	public String toRegPage(){
		return "regPage" ;
	}	
	
	/**
	 * �����û�ע��
	 */
	public String doReg(){
		
		if(!((String)session.get("random")).equalsIgnoreCase(captcha) && (!captcha.equals("")&&captcha!=null)){
			fieldError=1;
			email=model.getEmail();
			password=model.getPassword();
			confirmPassword1=confirmPassword;
			addFieldError("captcha", "��֤���������");
			return "input";
		}else{
			
			if(userService.isRegisted(model.getEmail())){
				fieldError=2;
				email=model.getEmail();
				password=model.getPassword();
				confirmPassword1=confirmPassword;
				addFieldError("email", "�����ѱ�ע��");
				return "input";
			}else{				
				//�������
				model.setPassword(DataUtil.md5(model.getPassword()));
				userService.saveEntity(model);
				return SUCCESS ;	
			}	
		}
	}
		
	/**
	 * У��
	 */
//	public void validate() {		
//		if(!((String)session.get("random")).equalsIgnoreCase(captcha) && (!captcha.equals("")&&captcha!=null)){
//			fieldError=1;
//			email=model.getEmail();
//			password=model.getPassword();
//			confirmPassword1=confirmPassword;
//			addFieldError("captcha", "��֤���������");
//		}
//		 
//		if(userService.isRegisted(model.getEmail())){
//			fieldError=2;
//			email=model.getEmail();
//			password=model.getPassword();
//			confirmPassword1=confirmPassword;
//			addFieldError("email", "�����ѱ�ע��");
//		}
//	}

	public int getFieldError() {
		return fieldError;
	}

	public void setFieldError(int fieldError) {
		this.fieldError = fieldError;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		   this.session = session;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword1() {
		return confirmPassword1;
	}

	public void setConfirmPassword1(String confirmPassword1) {
		this.confirmPassword1 = confirmPassword1;
	}
	
}
