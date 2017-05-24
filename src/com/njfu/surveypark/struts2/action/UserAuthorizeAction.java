package com.njfu.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.User;
import com.njfu.surveypark.model.security.Role;
import com.njfu.surveypark.service.RoleService;
import com.njfu.surveypark.service.UserService;
/**
 * 用户授权Action
 * @author CK
 * 2015年3月25日下午2:21:51
 */
@Controller
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User> {

	private static final long serialVersionUID = -304291553813628984L;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	private List<User> allUsers;//所用用户集合
	private Integer userId ;
	private List<Role> noOwnRoles ;//用户没有的角色集合
	private Integer[] ownRoleIds;

	public List<User> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<Role> getNoOwnRoles() {
		return noOwnRoles;
	}
	public void setNoOwnRoles(List<Role> noOwnRoles) {
		this.noOwnRoles = noOwnRoles;
	}
	public Integer[] getOwnRoleIds() {
		return ownRoleIds;
	}
	public void setOwnRoleIds(Integer[] ownRoleIds) {
		this.ownRoleIds = ownRoleIds;
	}
	/**
	 * 查找所用用户信息，显示在列表	
	 * @return
	 */
	public String findAllUsers(){
		this.allUsers = userService.findAllEntities();
		return "userAuthorizeListPage" ;
	}

	/**
	 * 修改授权
	 * @return
	 */
	public String editAuthorize(){
		this.model = userService.getEntity(userId);
		this.noOwnRoles = roleService.findRolesNotInRange(model.getRoles());
		return "userAuthorizePage" ;
	}
	
	/**
	 * 更新授权
	 * @return
	 */
	public String updateAuthorize(){
		userService.updateAuthorize(model,ownRoleIds);
		return "findAllUsersAction" ;
	}
	
	/**
	 * 清除授权
	 * @return
	 */
	public String clearAuthorize(){
		userService.clearAuthorize(userId);
		return "findAllUsersAction" ;
	}
}
