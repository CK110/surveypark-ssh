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
 * �û���ȨAction
 * @author CK
 * 2015��3��25������2:21:51
 */
@Controller
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User> {

	private static final long serialVersionUID = -304291553813628984L;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	private List<User> allUsers;//�����û�����
	private Integer userId ;
	private List<Role> noOwnRoles ;//�û�û�еĽ�ɫ����
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
	 * ���������û���Ϣ����ʾ���б�	
	 * @return
	 */
	public String findAllUsers(){
		this.allUsers = userService.findAllEntities();
		return "userAuthorizeListPage" ;
	}

	/**
	 * �޸���Ȩ
	 * @return
	 */
	public String editAuthorize(){
		this.model = userService.getEntity(userId);
		this.noOwnRoles = roleService.findRolesNotInRange(model.getRoles());
		return "userAuthorizePage" ;
	}
	
	/**
	 * ������Ȩ
	 * @return
	 */
	public String updateAuthorize(){
		userService.updateAuthorize(model,ownRoleIds);
		return "findAllUsersAction" ;
	}
	
	/**
	 * �����Ȩ
	 * @return
	 */
	public String clearAuthorize(){
		userService.clearAuthorize(userId);
		return "findAllUsersAction" ;
	}
}
