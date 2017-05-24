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
	private Integer sid;//接受sid
	
	private int pageNow = 1;    //当前页
	private int pageSize = 8;   //每页显示条目数
	private int totalPage;  //总页数
	private int totalSize;  //总条目数
	private String survey_name;//条件查询时，问卷的名字
	private List<Integer> pageList = new ArrayList<Integer>();	//设置属性，用来保存页码
	
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
	 * 首页
	 */
	public String toMain(){
		return "adminPage" ;
	}


	/**
	 * 查找所有开放的调查 （管理员） 
	 */
	public String findAllSurveys(){	
		totalSize = paginationService.getTotalSurvey();
		totalPage= PaginationUtil.getTotalPage(totalSize, pageSize);
		this.surveys = paginationService.paging(pageNow, pageSize);
		pageList =PaginationUtil.getPageList(totalPage);
		return "ManagerSurveyListPage";
	}
	
	/**
	 * 条件查询
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
	 * 查看问卷内容
	 */
	public String allSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "AllSurveyListPage";
	}
	
	/**
	 * 发送邮件
	 */
	public String sendHtmlMessage() {
		//根据SID 查找到创建人email
		Survey survey = surveyService.getSurvey(sid);
		String title = survey.getTitle();
		User user = survey.getUser();
		String email = user.getEmail();
		
		JavaMailSender sender = (JavaMailSender) context.getBean("mailSender");		
		try {   
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();   
			MimeMessage mailMessage = senderImpl.createMimeMessage();   
			//设置utf-8或GBK编码，否则邮件会有乱码   
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");   
		
			messageHelper.setTo(email);
			messageHelper.setFrom("chenkaic4233@163.com");  
			messageHelper.setSubject("调查派");
			//邮件内容，参数true，表示启用html格式   
			messageHelper.setText("<html><head></head><body><red><h3>警告：</h3></red>"
					+ "<h4>问卷标题："+title+"。  该问卷含有非法信息，已被删除！</h4>"
					+ "<h4>请不要发布含有非法信息的问卷，否则封号处理！</h4>"
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
