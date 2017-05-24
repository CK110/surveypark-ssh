package com.njfu.surveypark.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.njfu.surveypark.service.LogService;
import com.njfu.surveypark.util.LogUtil;
/**
 * 创建日志表的石英任务
 * @author CK
 * 2015年4月14日下午4:35:42
 */
public class CreateLogTablesTask  extends QuartzJobBean {
	private LogService logService ;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		//下一月
		String tableName = LogUtil.generateLogTableName(1);
		logService.createLogTable(tableName);
		
		//下两月
		tableName = LogUtil.generateLogTableName(2);
		logService.createLogTable(tableName);
		
		//下三月
		tableName = LogUtil.generateLogTableName(3);
		logService.createLogTable(tableName);
		
	}

}
