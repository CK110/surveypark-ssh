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
	 * ��ѯ�����б�
	 * @param user
	 * @return
	 */
	public List<Survey> findMySurveys(User user);
	
	/**
	 * �½�����
	 * @param user
	 * @return
	 */
	public Survey newSurvey(User user);

	/**
	 * ����id��ѯSurvey
	 * @param sid
	 * @return
	 */
	public Survey getSurvey(Integer sid);

	/**
	 * ����id��ѯSurvey,ͬʱЯ�����к��ӣ�����������쳣��
	 * @param sid
	 * @return
	 */
	public Survey getSurveyWithChildren(Integer sid);

	/**
	 * ���µ���
	 * @param model
	 */
	public void updateSurvey(Survey model);

	/**
	 * ����/����ҳ��
	 * @param model
	 */
	public void saveOrUpdatePage(Page model);
	
	
	/**
	 * ����id��ѯҳ��
	 * @param pid
	 * @return
	 */
	public Page getPage(Integer pid);
	
	/**
	 * ����/��������
	 * @param model
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * ɾ�����⣬����ɾ����
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
	 * �༭����
	 * @param qid
	 * @return
	 */
	public Question getQuestion(Integer qid);

	/**
	 * ��������
	 * @param sid
	 */
	public void clearAnswers(Integer sid);

	/**
	 * �ı����״̬�����Ż��߹ر�
	 * @param sid
	 */
	public void toggleStatus(Integer sid);

	/**
	 * ������飬�������е����б�
	 * @return
	 */
	public List<Survey> findAllAvailableSurveys();

	/**
	 * ��ȡ������ҳ
	 * @param sid
	 * @return
	 */
	public Page getFirstPage(Integer sid);

	/**
	 * ������� ��һ��
	 * @param currPid
	 * @return
	 */
	public Page getPrePage(Integer currPid);

	/**
	 * ������� ��һ��
	 * @param currPid
	 * @return
	 */
	public Page getNextPage(Integer currPid);
	
	/**
	 * TUDO ����⣬ ���������
	 * @param processAnswers
	 */
	public void saveAnswers(List<Answer> processAnswers);

	/**
	 * excel �������õ�question
	 * @param sid
	 * @return
	 */
	public List<Question> getQuestions(Integer sid);

	/**
	 * excel,�õ�answer
	 * @param sid
	 * @return
	 */
	public List<Answer> getAnswers(Integer sid);
	
}
