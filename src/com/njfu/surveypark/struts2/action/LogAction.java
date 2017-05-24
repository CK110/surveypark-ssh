package com.njfu.surveypark.struts2.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.security.Log;
import com.njfu.surveypark.service.LogService;

/**
 * ��־Action
 * @author CK
 * 2015��3��27������3:59:57
 */
@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	private static final long serialVersionUID = -6659542930467617714L;
	@Resource
	private LogService logService;
	private List<Log> logs ;//��־����
	//Ĭ�ϲ�ѯ��־���·���
	private int monthNum = 2 ;
	
	
	private int pageNow = 1;    //��ǰҳ
	private int pageSize = 3;   //ÿҳ��ʾ��Ŀ��
	private int totalPage;  //��ҳ��
	private int totalSize;  //����Ŀ��
	private List<Integer> pageList = new ArrayList<Integer>();	//�������ԣ���������ҳ��
	
	public int getMonthNum() {
		return monthNum;
	}
	public void setMonthNum(int monthNum) {
		this.monthNum = monthNum;
	}
	public List<Log> getLogs() {
		return logs;
	}
	public void setLogs(List<Log> logs) {
		this.logs = logs;
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
	public List<Integer> getPageList() {
		return pageList;
	}
	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
	/**
     * ����������־��¼
     * @return
     */
	public String findAllLogs(){
		this.logs = logService.findAllEntities();
		return "logListPage" ;
	}
	
	/**
	 * ��ѯ�������־ 
	 */
	public String findNearestLogs(){
		this.logs = logService.findNearestLogs(monthNum);
		return "logListPage" ;
	}

}
