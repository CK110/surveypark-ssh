package com.njfu.surveypark.service;

import com.njfu.surveypark.model.User;

/**
 * UserService
 */
public interface UserService extends BaseService<User> {
	
	/**
	 *  �ж�email�Ƿ�ռ��
	 */
	public boolean isRegisted(String email);

	/**
	 * ��֤��½��Ϣ
	 */
	public User validateLoginInfo(String email, String md5);

	/**
	 * ������Ȩ
	 * @param model
	 * @param ownRoleIds
	 */
	public void updateAuthorize(User model, Integer[] ownRoleIds);

	/**
	 * ɾ����Ȩ
	 * @param userId
	 */
	public void clearAuthorize(Integer userId);
}
