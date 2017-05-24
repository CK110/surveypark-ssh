package com.njfu.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.Page;
import com.njfu.surveypark.model.Question;
import com.njfu.surveypark.service.SurveyService;
/**
 * QuestionAction
 * @author CK
 *
 */
@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = 4126701384266156311L;

	@Resource
	private SurveyService surveyService;
	private Integer sid;
	
	private Integer pid;
	
	private Integer qid;
	
	

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	/**
	 * 到达选择题型页面
	 * @return
	 */
	public String toSelectQuestionType(){
		return "selectQuestionTypePage";
	}
	
	/**
	 * 到达问题设计页面
	 * @return
	 */
	public String toDesignQuestionPage(){
		return "" + model.getQuestionType();
	}
	
	/**
	 * 保存/更行问题
	 * @return
	 */
	public String saveOrUpdateQuestion(){
		Page p = new Page();
		p.setId(pid);
		//维护关联关系
		model.setPage(p);
		surveyService.saveOrUpdateQuestion(model);
		System.out.println(model);
		return "designSurveyAction";
	}
	
	/**
	 * 删除问题
	 */
	public String deleteQuestion(){
		//System.out.println(qid);
		surveyService.deleteQuestion(qid);
		return "designSurveyAction";
	}
	
	/**
	 * 编辑问题
	 */
	public String editQuestion(){
		this.model = surveyService.getQuestion(qid);
		return "" + model.getQuestionType();
	}
}
