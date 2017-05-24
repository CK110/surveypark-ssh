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
 * 登陆action
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
	//接受session的map
	private Map<String,Object> sessionMap ;
	
	private int fieldError;
	
	/**
	 * 到达登陆页面
	 */
	public String toLoginPage(){
		return "loginPage";
	}
	
	/**
	 * 进行登陆处理
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
	 * 校验登陆信息
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
			//初始化权限总和数组
			int maxPos = rightService.getMaxRightPos();
			user.setRightSum(new long[maxPos + 1]);
			//计算用户权限总和
			user.calculateRightSum();
			sessionMap.put("user", user);
		}

	}
	
	/**
	 * 首页
	 */
	public String toMain(){
		return "userPage" ;
	}

	/**
	 * 注销
	 * @return
	 */
	public String logOut(){
		sessionMap.clear();
		return "logOut";
	}
	//注入session的map
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
