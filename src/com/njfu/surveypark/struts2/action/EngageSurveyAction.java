package com.njfu.surveypark.struts2.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.Answer;
import com.njfu.surveypark.model.Page;
import com.njfu.surveypark.model.Survey;
import com.njfu.surveypark.service.PaginationService;
import com.njfu.surveypark.service.SurveyService;
import com.njfu.surveypark.util.PaginationUtil;
import com.njfu.surveypark.util.StringUtil;
import com.njfu.surveypark.util.ValidateUtil;
/**
 * EngageSurveyAction
 * @author CK
 *
 */
@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware,SessionAware,ParameterAware {

	private static final long serialVersionUID = -2828704622740218614L;
	//��ǰ�����key
	private static final String CURRENT_SURVEY = "current_survey";
	
	//���в�����map
	private static final String ALL_PARAMS_MAP = "all_params_map";
	
	@Resource
	private SurveyService surveyService;
	
	@Resource
	private PaginationService paginationService;
	
	private List<Survey> surveys ;
	
	//����ServletContext
	private ServletContext sc;
	
	//����sessionMap
	private Map<String, Object> sessionMap;
	
	//�������в�����map
	private Map<String, String[]> paramsMap;
	
	private Integer sid ;
	
	//��ǰҳ��
	private Page currPage ;
	
	private Integer currPid ;
	
	private int pageNow = 1;    //��ǰҳ
	private int pageSize = 15;   //ÿҳ��ʾ��Ŀ��
	private int totalPage;  //��ҳ��
	private int totalSize;  //����Ŀ��
	private String survey_name;//������ѯʱ���ʾ������
	private List<Integer> pageList = new ArrayList<Integer>();	//�������ԣ���������ҳ��
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Page getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}

	public Integer getCurrPid() {
		return currPid;
	}

	public void setCurrPid(Integer currPid) {
		this.currPid = currPid;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
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

	public String getSurvey_name() {
		return survey_name;
	}

	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

	/**
	 * �������п��ŵĵ���
	 * @return
	 */
	public String findAllAvailableSurveys(){
		//this.surveys = paginationService.findAllAvailableSurveys();		
		totalSize = paginationService.getTotalSurvey();
		totalPage= PaginationUtil.getTotalPage(totalSize, pageSize);
		this.surveys = paginationService.paging(pageNow, pageSize);
		pageList =PaginationUtil.getPageList(totalPage);
		return "engageSurveyListPage";
	}
	
	/**
	 * ������ѯ
	 * @return
	 */
	public String findAllAvailableSurveysByTitle(){
		totalSize = paginationService.getTotalSurvey(survey_name);
		totalPage= PaginationUtil.getTotalPage(totalSize, pageSize);
		this.surveys = paginationService.paging(survey_name,pageNow, pageSize);
		pageList =PaginationUtil.getPageList(totalPage);
		return "engageSurveyListPageByTitle";
	}
		
	/**
	 * ��ȡlogo,Ӧ�ô���һ���ļ�������
	 */
	public String getImageUrl(String path){
		if(ValidateUtil.isValid(path)){
			String absPath = sc.getRealPath(path);
			File f = new File(absPath);
			if(f.exists()){
				//System.out.println(sc.getContextPath());
				return sc.getContextPath() + path ;	
			}
		}
		return sc.getContextPath() + "/img/question.jpg"  ;
	}

	/**
	 * ��ȡ�����һҳ
	 */
	public String entry(){
		//��ѯ��ҳ
		this.currPage = surveyService.getFirstPage(sid);
		//���survey--> session
		sessionMap.put(CURRENT_SURVEY, currPage.getSurvey());
		//��������в����Ĵ�map -->session��(�û�����𰸺ͻ���).
		sessionMap.put(ALL_PARAMS_MAP,new HashMap<Integer,Map<String,String[]>>());
		return "engageSurveyPage" ;
	}
	
	/**
	 * ���������� 
	 */
	public String doEngageSurvey(){
		String submitName = getSubmitName();
		//��һҳ
		if(submitName.endsWith("pre")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getPrePage(currPid);
			return "engageSurveyPage" ;
		}
		//��һҳ
		else if(submitName.endsWith("next")){
			mergeParamsIntoSession();
			this.currPage = surveyService.getNextPage(currPid);
			return "engageSurveyPage" ;
		}
		//�ύ
		else if(submitName.endsWith("done")){
			mergeParamsIntoSession();
			//TODO�����	
			surveyService.saveAnswers(processAnswers());
			clearSessionData();
			return "engageSurveyAction" ;
		}
		//ȡ��
		else if(submitName.endsWith("exit")){
			clearSessionData();
			return "engageSurveyAction" ;
		}
		return null ;
	}
	
	/**
	 * �����
	 */
	private List<Answer> processAnswers() {
		//����ʽ��ѡ��ť��map
		Map<Integer, String> matrixRadioMap = new HashMap<Integer,String>();
		//���д𰸵ļ���
		List<Answer> answers = new ArrayList<Answer>();
		Answer a = null ;
		String key = null ;
		String[] value = null ;
		Map<Integer, Map<String,String[]>> allMap = getAllParamsMap();
		for(Map<String,String[]> map : allMap.values()){
			for(Entry<String, String[]> entry : map.entrySet()){
				key = entry.getKey();
				value = entry.getValue();
				//��������q��ͷ�Ĳ���
				if(key.startsWith("q")){
					//�������
					if(!key.contains("other") && !key.contains("_")){
						a = new Answer();
						a.setAnswerIds(StringUtil.arr2Str(value));//answerids
						a.setQuestionId(getQid(key));//qid
						a.setSurveyId(getCurrentSurvey().getId());//sid
						a.setOtherAnswer(StringUtil.arr2Str(map.get(key + "other")));//otherAnswer
						answers.add(a);
					}
					//����ʽ��ѡ��ť
					else if(key.contains("_")){
						Integer radioQid = getMatrixRadaioQid(key);
						String oldValue = matrixRadioMap.get(radioQid);
						if(oldValue == null){
							matrixRadioMap.put(radioQid, StringUtil.arr2Str(value));
						}
						else{
							matrixRadioMap.put(radioQid, oldValue + "," + StringUtil.arr2Str(value));
						}
					}
				}
			}
		}
		
		//��������ʽ��ѡ��ť
		processMatrixRadioMap(matrixRadioMap,answers);
		return answers; 
	}
	
	/**
	 * 
	 * @param matrixRadioMap
	 * @param answers
	 */
	private void processMatrixRadioMap(Map<Integer, String> matrixRadioMap,
			List<Answer> answers) {
		Answer a = null ;
		Integer key = null ;
		String value  = null ;
		for(Entry<Integer,String> entry : matrixRadioMap.entrySet()){
			key = entry.getKey();
			value = entry.getValue();
			a = new Answer();
			a.setAnswerIds(value);//answerids
			a.setQuestionId(key);//qid
			a.setSurveyId(getCurrentSurvey().getId());
			answers.add(a);
		}
	}

	/**
	 * ��ȡ����ʽ����id:q12_0 ---> 12
	 * @param key
	 * @return
	 */
	private Integer getMatrixRadaioQid(String key) {
		return Integer.parseInt(key.substring(1, key.lastIndexOf("_")));
	}

	/**
	 * ��ȡ��ǰ�������
	 * @return
	 */
	private Survey getCurrentSurvey() {
		return (Survey)sessionMap.get(CURRENT_SURVEY);
	}

	/**
	 * ��ȡ����id--> q12 --> 12
	 * @param key
	 * @return
	 */
	private Integer getQid(String key) {
		return Integer.parseInt(key.substring(1));
	}

	/**
	 * ���session����
	 */
	private void clearSessionData() {
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_PARAMS_MAP);
	}

	/**
	 * �ϲ�������session��
	 */
	private void mergeParamsIntoSession() {
		Map<Integer,Map<String,String[]>> allParamsMap = getAllParamsMap();
		allParamsMap.put(currPid, paramsMap);
	}

	/**
	 * ��ȡsession������в�����map
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, Map<String, String[]>> getAllParamsMap() {
		return (Map<Integer, Map<String, String[]>>)sessionMap.get(ALL_PARAMS_MAP);
	}

	/**
	 * ����ύ��ť������
	 */
	private String getSubmitName() {
		for(String key : paramsMap.keySet()){
			if(key.startsWith("submit_")){
				return key ;
			}
		}
		return null;
	}
	
	/**
	 * ���ñ��,���ڴ𰸻���,��Ҫ����radio|checkbox|select��ѡ�б��
	 * @param name ��������
	 * @param value ����ֵ
	 * @param selTag ���ñ�ǩ
	 * @return
	 */
	public String setTag(String name,String value,String selTag){
		Map<String, String[]> map = getAllParamsMap().get(currPage.getId());
		String[] values = map.get(name);
		if(StringUtil.contains(values,value)){
			return selTag;
		}
		return "" ;
	}
	
	/**
	 * ���ñ��,���ڴ𰸻���,�����ı������
	 */
	public String setText(String name){
		Map<String, String[]> map = getAllParamsMap().get(currPage.getId());
		String[] values = map.get(name);
		return "value='"+values[0]+"'" ;
	}


	/**
	 * ע���ύ�����в�����map
	 */
	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.paramsMap = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
}
