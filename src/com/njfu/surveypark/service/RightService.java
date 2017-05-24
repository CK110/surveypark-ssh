package com.njfu.surveypark.service;

import java.util.List;
import java.util.Set;

import com.njfu.surveypark.model.security.Right;

/**
 * Ȩ��service
 * @author CK
 * 2015��3��16������10:19:02
 */
public interface RightService extends BaseService<Right> {

	/**
	 * ����/����Ȩ��
	 * @param model
	 */
	public void saveOrUpdateRight(Right model);

	/**
	 * ����url׷��Ȩ��
	 * @param url
	 */
	public void appendRightByURL(String url);
	
	/**
	 * ��������Ȩ��
	 * @param allRights
	 */
	public void batchUpdateRights(List<Right> allRights);

	/**
	 * ��ѯ��ָ����Χ�ڵ�Ȩ��
	 * @param ids
	 * @return
	 */
	public List<Right> findRightsInRange(Integer[] ids);
	
	/**
	 *  ��ѯ����ָ����Χ�ڵ�Ȩ��
	 * @param rights
	 * @return
	 */
	public List<Right> findRightsNotInRange(Set<Right> rights);

	/**
	 * �õ����Ȩ��λ
	 * @return
	 */
	public int getMaxRightPos();
	
	
}
