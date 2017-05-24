package com.njfu.surveypark.struts2.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.Question;
import com.njfu.surveypark.model.statistics.QuestionStatisticsModel;
import com.njfu.surveypark.service.StatisticsService;

/**
 * MatrixStatisticsAction  相对于ChartOutPutAction
 * @author CK
 * 2015年3月3日下午3:12:43
 */
@Controller
@Scope("prototype")
public class MatrixStatisticsAction extends BaseAction<Question> {

	private static final long serialVersionUID = 1753782855753997685L;
	
	@Resource
	private StatisticsService ss ;
	
	private Integer qid ;
	
	private QuestionStatisticsModel qsm ;

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public QuestionStatisticsModel getQsm() {
		return qsm;
	}

	public void setQsm(QuestionStatisticsModel qsm) {
		this.qsm = qsm;
	}
	
	public String execute() throws Exception {
		//先进行统计
		//this.qsm = ss.statistics(qid);
		//return "" + qsm.getQuestion().getQuestionType();
		return "" + 7;
	}



}
