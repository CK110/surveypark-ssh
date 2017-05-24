package com.njfu.surveypark.model.security;

import java.util.HashSet;
import java.util.Set;

import com.njfu.surveypark.model.BaseEntity;
/**
 * 角色
 * @author CK
 * 2015年3月11日下午1:45:00
 */
public class Role extends BaseEntity{
	private static final long serialVersionUID = 4112515801304082619L;
	private Integer id;
	private String roleName;
	private String roleValue;
	private String roleDesc;
	private Set<Right> rights = new HashSet<Right>(); //权限集合
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleValue() {
		return roleValue;
	}
	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public Set<Right> getRights() {
		return rights;
	}
	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}
}
