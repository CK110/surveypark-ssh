package com.njfu.surveypark.struts2.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.User;
import com.njfu.surveypark.service.RightService;
import com.njfu.surveypark.service.UserService;
import com.njfu.surveypark.util.DataUtil;

/**
 * ��½action
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware {

	private static final long serialVersionUID = -5224235546505400932L;
	
	@Resource
	private UserService userService ;
	
	@Resource
	private RightService rightService;
	private String email;
	private String password;
	//����session��map
	private Map<String,Object> sessionMap ;
	
	private int fieldError;
	
	/**
	 * �����½ҳ��
	 */
	public String toLoginPage(){
		return "loginPage";
	}
	
	/**
	 * ���е�½����
	 */
	public String doLogin(){
//		User user = userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
//		sessionMap.put("user", user);
		User user = (User) sessionMap.get("user");
		if(user.isSuperAdmin()){
			return "adminPage";
		}else{
			return "userPage";
		}
	}

	/**
	 * У���½��Ϣ
	 */
	public void validateDoLogin(){
		User user = userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
		if(user == null){
			fieldError=1;
			email=model.getEmail();
			password=model.getPassword();
			addActionError("xxxx");
		}
		else{
			//��ʼ��Ȩ���ܺ�����
			int maxPos = rightService.getMaxRightPos();
			user.setRightSum(new long[maxPos + 1]);
			//�����û�Ȩ���ܺ�
			user.calculateRightSum();
			sessionMap.put("user", user);
		}

	}
	
	/**
	 * ��ҳ
	 */
	public String toMain(){
		return "userPage" ;
	}

	/**
	 * ע��
	 * @return
	 */
	public String logOut(){
		sessionMap.clear();
		return "logOut";
	}
	//ע��session��map
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0 ;
	}

	public int getFieldError() {
		return fieldError;
	}

	public void setFieldError(int fieldError) {
		this.fieldError = fieldError;
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
	
	
}
