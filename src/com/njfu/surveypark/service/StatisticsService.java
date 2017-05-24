package com.njfu.surveypark.service;

import com.njfu.surveypark.model.statistics.QuestionStatisticsModel;

/**
 * 统计服务
 * @author CK
 *
 */
public interface StatisticsService {
	public QuestionStatisticsModel statistics(Integer qid);
}
