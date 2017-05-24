package com.njfu.surveypark.service;

import java.util.List;
import java.util.Set;

import com.njfu.surveypark.model.security.Right;

/**
 * 权限service
 * @author CK
 * 2015年3月16日上午10:19:02
 */
public interface RightService extends BaseService<Right> {

	/**
	 * 保存/更新权限
	 * @param model
	 */
	public void saveOrUpdateRight(Right model);

	/**
	 * 按照url追加权限
	 * @param url
	 */
	public void appendRightByURL(String url);
	
	/**
	 * 批量更新权限
	 * @param allRights
	 */
	public void batchUpdateRights(List<Right> allRights);

	/**
	 * 查询在指定范围内的权限
	 * @param ids
	 * @return
	 */
	public List<Right> findRightsInRange(Integer[] ids);
	
	/**
	 *  查询不在指定范围内的权限
	 * @param rights
	 * @return
	 */
	public List<Right> findRightsNotInRange(Set<Right> rights);

	/**
	 * 得到最大权限位
	 * @return
	 */
	public int getMaxRightPos();
	
	
}
