package com.njfu.surveypark.service;

import java.util.List;
import java.util.Set;

import com.njfu.surveypark.model.security.Role;
/**
 * 角色Service
 * @author CK
 * 2015年3月25日下午2:58:22
 */
public interface RoleService extends BaseService<Role> {

	/**
	 * 保存或者更新角色
	 * @param model
	 * @param ownRightIds
	 */
	void saveOrUpdateRole(Role model, Integer[] ownRightIds);

	/**
	 * 查询不在指定范围中的角色
	 * @param roles
	 * @return
	 */
	List<Role> findRolesNotInRange(Set<Role> roles);

	/**
	 * 查询指定范围中的角色
	 * @param ownRoleIds
	 * @return
	 */
	List<Role> findRolesInRange(Integer[] ownRoleIds);

}
