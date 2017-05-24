package com.njfu.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.Page;
import com.njfu.surveypark.model.Survey;
import com.njfu.surveypark.service.SurveyService;


@Controller
@Scope("prototype")
public class PageAction extends BaseAction<Page> {

	private static final long serialVersionUID = 7161144599403995825L;
	
	//注入SurveyService
	@Resource
	private SurveyService surveyService;
	
	private Integer sid;
	
	private Integer pid;
	
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
	 *到达添加page的页面
	 */
	public String toAddPage(){
		return "addPagePage";
	}
	
	/**
	 * 保存/更新页面
	 */
	public String saveOrUpdatePage(){
		//维护关联关系
		Survey s = new Survey();
		s.setId(sid);
		model.setSurvey(s);
		System.out.println(model);
		surveyService.saveOrUpdatePage(model);
		return "designSurveyAction";
	}

	/**
	 * 达到编辑Page页面
	 * @return
	 */
	public String editPage(){
		this.model = surveyService.getPage(pid);
		return "editPagePage";
	}
	
	/**
	 * delete page
	 */
	public String deletePage(){
		surveyService.deletePage(pid);
		return "designSurveyAction" ;
	}
	
}
