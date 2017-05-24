package com.njfu.surveypark.model.statistics;

import java.util.ArrayList;
import java.util.List;

import com.njfu.surveypark.model.Question;

/**
 * 问题统计模型
 * @author CK
 * 2015年3月3日下午2:18:49
 */
public class QuestionStatisticsModel {
	private Question question;//
	private int count;// 回答该问题的人数

	private List<OptionStatisticsModel> osms = new ArrayList<OptionStatisticsModel>();

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<OptionStatisticsModel> getOsms() {
		return osms;
	}

	public void setOsms(List<OptionStatisticsModel> osms) {
		this.osms = osms;
	}
	
	
}
