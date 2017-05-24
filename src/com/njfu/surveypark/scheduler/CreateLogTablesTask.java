package com.njfu.surveypark.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.njfu.surveypark.service.LogService;
import com.njfu.surveypark.util.LogUtil;
/**
 * ������־���ʯӢ����
 * @author CK
 * 2015��4��14������4:35:42
 */
public class CreateLogTablesTask  extends QuartzJobBean {
	private LogService logService ;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		//��һ��
		String tableName = LogUtil.generateLogTableName(1);
		logService.createLogTable(tableName);
		
		//������
		tableName = LogUtil.generateLogTableName(2);
		logService.createLogTable(tableName);
		
		//������
		tableName = LogUtil.generateLogTableName(3);
		logService.createLogTable(tableName);
		
	}

}
