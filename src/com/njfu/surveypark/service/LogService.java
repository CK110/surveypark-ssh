package com.njfu.surveypark.service;

import java.util.List;

import com.njfu.surveypark.model.security.Log;
/**
 * ��־service
 * @author CK
 * 2015��3��27������4:05:27
 */
public interface LogService extends BaseService<Log> {

	/**
	 * ͨ������������־��
	 * @param tableName
	 */
	public void createLogTable(String tableName);

	public List<Log> findNearestLogs(int monthNum);


}
