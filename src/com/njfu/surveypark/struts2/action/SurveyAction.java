package com.njfu.surveypark.struts2.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.Survey;
import com.njfu.surveypark.model.User;
import com.njfu.surveypark.service.PaginationService;
import com.njfu.surveypark.service.SurveyService;
import com.njfu.surveypark.util.PaginationUtil;
/**
 * SurveyAction
 */
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements SessionAware{
	
	private static final long serialVersionUID = 587149269950475258L;
	@Resource
	private SurveyService surveyService;	
	@Resource
	private PaginationService paginationService;
	private List<Survey> mySurveys;	//调查集合
	private Map<String, Object> sessionMap;	//接受sessionMap
	private Integer sid;//接受sid
	private String inputPage ;//动态错误页指定
	
	private int pageNow = 1;    //当前页
	private int pageSize = 8;   //每页显示条目数
	private int totalPage;  //总页数
	private int totalSize;  //总条目数
	private int survey_status=3; //条件查询时，所需要的问卷是否发布的状态码
	private String survey_name;//条件查询时，问卷的名字
	private List<Integer> pageList = new ArrayList<Integer>();	//设置属性，用来保存页码
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public List<Survey> getMySurveys() {
		return mySurveys;
	}
	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}
	public String getInputPage() {
		return inputPage;
	}
	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}
	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getSurvey_status() {
		return survey_status;
	}

	public void setSurvey_status(int survey_status) {
		this.survey_status = survey_status;
	}

	public String getSurvey_name() {
		return survey_name;
	}

	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}

	/**
	 * 查询我的调查列表,获取我的所有调查
	 */
	public String mySurveys(){
		User user= (User) sessionMap.get("user");
		totalSize = paginationService.getTotalSurvey(user);
		totalPage= PaginationUtil.getTotalPage(totalSize, pageSize);
		this.mySurveys = paginationService.paging(user,pageNow, pageSize);
		pageList =PaginationUtil.getPageList(totalPage);
		return "mySurveyListPage";
	}
	
	/**
	 * 按条件查询我的调查列表,获取我的所有调查
	 */
	public String searchSurveyByStatus(){
		User user= (User) sessionMap.get("user");
		totalSize = paginationService.getTotalSurvey(user,survey_status);
		totalPage= PaginationUtil.getTotalPage(totalSize, pageSize);
		this.mySurveys = paginationService.paging(user,survey_status,pageNow, pageSize);
		pageList =PaginationUtil.getPageList(totalPage);
		return "mySurveyListPageByStatus";
	}
	
	/**
	 * 按照调查问卷的title,获取我的所有调查
	 */
	public String searchSurveyByTitle(){
		System.out.println(survey_name);
		User user= (User) sessionMap.get("user");
		totalSize = paginationService.getTotalSurvey(user,survey_name);
		totalPage= PaginationUtil.getTotalPage(totalSize, pageSize);
		this.mySurveys = paginationService.paging(user,survey_name,pageNow, pageSize);
		pageList =PaginationUtil.getPageList(totalPage);
		return "mySurveyListPageByTitle";
	}
	
	/**
	 * 新建调查列表
	 */
	public String newSurvey(){
		User user= (User) sessionMap.get("user");
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage";
	}

	/**
	 * 设计调查（我的调查页面的设计按钮）
	 */
	public String designSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage";
	}
	
	/**
	 * 编辑调查（新建调查页面，修改调查名等信息）
	 */
	public String editSurvey(){
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}
	
	/**
	 * 更新调查（编辑调查的确定按钮引发的事件）
	 */
	public String updateSurvey(){
		this.sid = model.getId();
		User user= (User) sessionMap.get("user");
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "designSurveyAction";
	}
	
	/**
	 * 该方法只在updateSurvey之前运行
	 */
	public void prepareUpdateSurvey(){
		inputPage = "/editSurvey.jsp" ;
	}
	
	/**
	 * 删除调查
	 */
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "findMySurveysAction" ;
	}
	
	/**
	 * 清除调查的答案
	 */
	public String clearAnswers(){
		surveyService.clearAnswers(sid);
		return "findMySurveysAction" ;
	}
	
	/**
	 * 改变调查状态，开放或者关闭
	 */
	public String toggleStatus(){
		surveyService.toggleStatus(sid);
		return "findMySurveysAction" ;
	}
	
	/**
	 * 到达分析调查页面
	 * @return
	 */
	public String analyzeSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "analyzeSurveyListPage" ;
	}
	

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	
}
