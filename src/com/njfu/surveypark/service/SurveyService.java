package com.njfu.surveypark.service;

import java.util.List;





















import com.njfu.surveypark.model.Answer;
import com.njfu.surveypark.model.Page;
import com.njfu.surveypark.model.Question;
import com.njfu.surveypark.model.Survey;
import com.njfu.surveypark.model.User;

/**
 * SurveyService
 * @author CK
 *
 */
public interface SurveyService {
	
	/**
	 * 查询调查列表
	 * @param user
	 * @return
	 */
	public List<Survey> findMySurveys(User user);
	
	/**
	 * 新建调查
	 * @param user
	 * @return
	 */
	public Survey newSurvey(User user);

	/**
	 * 按照id查询Survey
	 * @param sid
	 * @return
	 */
	public Survey getSurvey(Integer sid);

	/**
	 * 按照id查询Survey,同时携带所有孩子（解决懒加载异常）
	 * @param sid
	 * @return
	 */
	public Survey getSurveyWithChildren(Integer sid);

	/**
	 * 更新调查
	 * @param model
	 */
	public void updateSurvey(Survey model);

	/**
	 * 保存/更新页面
	 * @param model
	 */
	public void saveOrUpdatePage(Page model);
	
	
	/**
	 * 按照id查询页面
	 * @param pid
	 * @return
	 */
	public Page getPage(Integer pid);
	
	/**
	 * 保存/更新问题
	 * @param model
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * 删除问题，并且删除答案
	 * @param qid
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * delete page
	 * @param pid
	 */
	public void deletePage(Integer pid);
	
	/**
	 * delete survey
	 * @param sid
	 */
	public void deleteSurvey(Integer sid);
	
	/**
	 * 编辑问题
	 * @param qid
	 * @return
	 */
	public Question getQuestion(Integer qid);

	/**
	 * 清除调查答案
	 * @param sid
	 */
	public void clearAnswers(Integer sid);

	/**
	 * 改变调查状态，开放或者关闭
	 * @param sid
	 */
	public void toggleStatus(Integer sid);

	/**
	 * 参与调查，返回所有调查列表
	 * @return
	 */
	public List<Survey> findAllAvailableSurveys();

	/**
	 * 获取调查首页
	 * @param sid
	 * @return
	 */
	public Page getFirstPage(Integer sid);

	/**
	 * 参与调查 上一步
	 * @param currPid
	 * @return
	 */
	public Page getPrePage(Integer currPid);

	/**
	 * 参与调查 下一步
	 * @param currPid
	 * @return
	 */
	public Page getNextPage(Integer currPid);
	
	/**
	 * TUDO 答案入库， 批量保存答案
	 * @param processAnswers
	 */
	public void saveAnswers(List<Answer> processAnswers);

	/**
	 * excel 操作，得到question
	 * @param sid
	 * @return
	 */
	public List<Question> getQuestions(Integer sid);

	/**
	 * excel,得到answer
	 * @param sid
	 * @return
	 */
	public List<Answer> getAnswers(Integer sid);
	
}
