package com.njfu.surveypark.service;

import java.util.List;

import com.njfu.surveypark.model.Survey;
import com.njfu.surveypark.model.User;
/**
 * 分页查询Service
 * @author CK
 * 2015年3月10日下午2:22:56
 */
public interface PaginationService {
	
	/**
	 * 查询所有Survey
	 * @param user
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<Survey> paging(User user,int pageNow, int pageSize);
	
	/**
	 * 得到当前用户所有调查的总数
	 * @param user
	 * @return
	 */
	public int getTotalSurvey(User user);
	
	/**
	 * 按状态码查询，<select/>下拉菜单查询方式，得到记录总数
	 * @param user
	 * @param survey_status
	 * @return
	 */
	public int getTotalSurvey(User user,int survey_status);
	
	/**
	 * 按状态码查询，<select/>下拉菜单查询方式，得到记录
	 * @param user
	 * @param survey_status
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<Survey> paging(User user,int survey_status,int pageNow, int pageSize);
	
	
	/**
	 * title 条件查询, 得到记录总数
	 * @param user
	 * @param survey_name
	 * @return
	 */
	public int getTotalSurvey(User user, String survey_name);

	/**
	 * title 条件查询, 得到记录
	 * @param user
	 * @param survey_name
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<Survey> paging(User user, String survey_name, int pageNow, int pageSize);

	//参与调查时
	
	/**
	 * 
	 * @return
	 */
	public int getTotalSurvey();

	/**
	 * 
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<Survey> paging(int pageNow, int pageSize);

	/**
	 * 条件查询
	 * @param survey_name
	 * @return
	 */
	public int getTotalSurvey(String survey_name);

	/**
	 * 条件查询
	 * @param survey_name
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<Survey> paging(String survey_name, int pageNow, int pageSize);
	

}
