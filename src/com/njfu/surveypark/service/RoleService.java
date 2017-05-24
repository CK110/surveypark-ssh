package com.njfu.surveypark.service;

import java.util.List;
import java.util.Set;

import com.njfu.surveypark.model.security.Role;
/**
 * ��ɫService
 * @author CK
 * 2015��3��25������2:58:22
 */
public interface RoleService extends BaseService<Role> {

	/**
	 * ������߸��½�ɫ
	 * @param model
	 * @param ownRightIds
	 */
	void saveOrUpdateRole(Role model, Integer[] ownRightIds);

	/**
	 * ��ѯ����ָ����Χ�еĽ�ɫ
	 * @param roles
	 * @return
	 */
	List<Role> findRolesNotInRange(Set<Role> roles);

	/**
	 * ��ѯָ����Χ�еĽ�ɫ
	 * @param ownRoleIds
	 * @return
	 */
	List<Role> findRolesInRange(Integer[] ownRoleIds);

}
