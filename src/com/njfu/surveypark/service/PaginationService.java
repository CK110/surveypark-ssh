package com.njfu.surveypark.service;

import java.util.List;

import com.njfu.surveypark.model.Survey;
import com.njfu.surveypark.model.User;
/**
 * ��ҳ��ѯService
 * @author CK
 * 2015��3��10������2:22:56
 */
public interface PaginationService {
	
	/**
	 * ��ѯ����Survey
	 * @param user
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<Survey> paging(User user,int pageNow, int pageSize);
	
	/**
	 * �õ���ǰ�û����е��������
	 * @param user
	 * @return
	 */
	public int getTotalSurvey(User user);
	
	/**
	 * ��״̬���ѯ��<select/>�����˵���ѯ��ʽ���õ���¼����
	 * @param user
	 * @param survey_status
	 * @return
	 */
	public int getTotalSurvey(User user,int survey_status);
	
	/**
	 * ��״̬���ѯ��<select/>�����˵���ѯ��ʽ���õ���¼
	 * @param user
	 * @param survey_status
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<Survey> paging(User user,int survey_status,int pageNow, int pageSize);
	
	
	/**
	 * title ������ѯ, �õ���¼����
	 * @param user
	 * @param survey_name
	 * @return
	 */
	public int getTotalSurvey(User user, String survey_name);

	/**
	 * title ������ѯ, �õ���¼
	 * @param user
	 * @param survey_name
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<Survey> paging(User user, String survey_name, int pageNow, int pageSize);

	//�������ʱ
	
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
	 * ������ѯ
	 * @param survey_name
	 * @return
	 */
	public int getTotalSurvey(String survey_name);

	/**
	 * ������ѯ
	 * @param survey_name
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<Survey> paging(String survey_name, int pageNow, int pageSize);
	

}
