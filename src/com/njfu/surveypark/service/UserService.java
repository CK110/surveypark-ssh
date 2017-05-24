package com.njfu.surveypark.service;

import com.njfu.surveypark.model.User;

/**
 * UserService
 */
public interface UserService extends BaseService<User> {
	
	/**
	 *  判断email是否占用
	 */
	public boolean isRegisted(String email);

	/**
	 * 验证登陆信息
	 */
	public User validateLoginInfo(String email, String md5);

	/**
	 * 更新授权
	 * @param model
	 * @param ownRoleIds
	 */
	public void updateAuthorize(User model, Integer[] ownRoleIds);

	/**
	 * 删除授权
	 * @param userId
	 */
	public void clearAuthorize(Integer userId);
}
