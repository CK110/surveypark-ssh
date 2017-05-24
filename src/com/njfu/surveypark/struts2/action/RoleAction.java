package com.njfu.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.security.Right;
import com.njfu.surveypark.model.security.Role;
import com.njfu.surveypark.service.RightService;
import com.njfu.surveypark.service.RoleService;
import com.njfu.surveypark.struts2.action.BaseAction;
/**
 * 角色Action
 * @author CK
 * 2015年3月18日下午3:01:24
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = -3270697573788462556L;
	
	@Resource
	private RoleService roleService ;
	@Resource
	private RightService rightService ;
	
	private List<Role> allRoles ; //所有角色集合
	private List<Right> noOwnRights ;//角色没有的权限集合
	private Integer roleId ;//角色ID
	private Integer[] ownRightIds ;	//角色拥有的权限id数组
	public List<Role> getAllRoles() {
		return allRoles;
	}
	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}
	public List<Right> getNoOwnRights() {
		return noOwnRights;
	}
	public void setNoOwnRights(List<Right> noOwnRights) {
		this.noOwnRights = noOwnRights;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer[] getOwnRightIds() {
		return ownRightIds;
	}
	public void setOwnRightIds(Integer[] ownRightIds) {
		this.ownRightIds = ownRightIds;
	}
	
	/**
	 * 查询所有角色信息，返回角色list 集合
	 * @return
	 */
	public String findAllRoles(){
		this.allRoles = roleService.findAllEntities();
		return "roleListPage" ;
	}
	
	/**
	 * 到达增加角色页面
	 * @return
	 */
	public String toAddRolePage(){
		this.noOwnRights = rightService.findAllEntities();
		return "addRolePage" ;
	}
	
	/**
	 * 保存更新角色
	 * @return
	 */
	public String saveOrUpdateRole(){
		roleService.saveOrUpdateRole(model,ownRightIds);
		return "findAllRolesAction" ;
	}
	
	/**
	 * 编辑角色
	 * @return
	 */
	public String editRole(){
		this.model = roleService.getEntity(roleId);
		this.noOwnRights = rightService.findRightsNotInRange(model.getRights());
		return "editRolePage" ;
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	public String deleteRole(){
		Role r = new Role();
		r.setId(roleId);
		roleService.deleteEntity(r);
		return "findAllRolesAction" ;
	}
	
}
