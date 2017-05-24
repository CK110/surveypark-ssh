package com.njfu.surveypark.struts2.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.Survey;
import com.njfu.surveypark.model.User;
import com.njfu.surveypark.service.PaginationService;
import com.njfu.surveypark.service.SurveyService;
import com.njfu.surveypark.util.PaginationUtil;

@Controller
@Scope("prototype")
public class ManagerAction extends BaseAction<Survey> implements ApplicationContextAware{

	private static final long serialVersionUID = -6124729412576210815L;

	@Resource
	private SurveyService surveyService;
	private ApplicationContext context;
	
	@Resource
	private PaginationService paginationService;
	
	private List<Survey> surveys ;
	private Integer sid;//����sid
	
	private int pageNow = 1;    //��ǰҳ
	private int pageSize = 8;   //ÿҳ��ʾ��Ŀ��
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
	 * ��ҳ
	 */
	public String toMain(){
		return "adminPage" ;
	}


	/**
	 * �������п��ŵĵ��� ������Ա�� 
	 */
	public String findAllSurveys(){	
		totalSize = paginationService.getTotalSurvey();
		totalPage= PaginationUtil.getTotalPage(totalSize, pageSize);
		this.surveys = paginationService.paging(pageNow, pageSize);
		pageList =PaginationUtil.getPageList(totalPage);
		return "ManagerSurveyListPage";
	}
	
	/**
	 * ������ѯ
	 * @return
	 */
	public String findAllSurveysByTitle(){
		totalSize = paginationService.getTotalSurvey(survey_name);
		totalPage= PaginationUtil.getTotalPage(totalSize, pageSize);
		this.surveys = paginationService.paging(survey_name,pageNow, pageSize);
		pageList =PaginationUtil.getPageList(totalPage);
		return "ManagerSurveyListPageByTitle";
	}
	
	/**
	 * �鿴�ʾ�����
	 */
	public String allSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "AllSurveyListPage";
	}
	
	/**
	 * �����ʼ�
	 */
	public String sendHtmlMessage() {
		//����SID ���ҵ�������email
		Survey survey = surveyService.getSurvey(sid);
		String title = survey.getTitle();
		User user = survey.getUser();
		String email = user.getEmail();
		
		JavaMailSender sender = (JavaMailSender) context.getBean("mailSender");		
		try {   
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();   
			MimeMessage mailMessage = senderImpl.createMimeMessage();   
			//����utf-8��GBK���룬�����ʼ���������   
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");   
		
			messageHelper.setTo(email);
			messageHelper.setFrom("chenkaic4233@163.com");  
			messageHelper.setSubject("������");
			//�ʼ����ݣ�����true����ʾ����html��ʽ   
			messageHelper.setText("<html><head></head><body><red><h3>���棺</h3></red>"
					+ "<h4>�ʾ���⣺"+title+"��  ���ʾ��зǷ���Ϣ���ѱ�ɾ����</h4>"
					+ "<h4>�벻Ҫ�������зǷ���Ϣ���ʾ������Ŵ���</h4>"
					+"</body></html>", true);   
			sender.send(mailMessage);   
		} catch (Exception e) {   
			throw new RuntimeException(e);
		}  
		return "ManagerAction";
	}
	

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context=arg0;
		
	}
}
