package com.njfu.surveypark.service;

import java.util.List;

import com.njfu.surveypark.model.security.Log;
/**
 * 日志service
 * @author CK
 * 2015年3月27日下午4:05:27
 */
public interface LogService extends BaseService<Log> {

	/**
	 * 通过表明创建日志表
	 * @param tableName
	 */
	public void createLogTable(String tableName);

	public List<Log> findNearestLogs(int monthNum);


}
