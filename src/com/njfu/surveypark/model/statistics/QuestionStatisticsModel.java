package com.njfu.surveypark.model.statistics;

import java.util.ArrayList;
import java.util.List;

import com.njfu.surveypark.model.Question;

/**
 * ����ͳ��ģ��
 * @author CK
 * 2015��3��3������2:18:49
 */
public class QuestionStatisticsModel {
	private Question question;//
	private int count;// �ش�����������

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
