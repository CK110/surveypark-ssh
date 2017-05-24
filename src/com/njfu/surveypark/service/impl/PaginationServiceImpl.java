package com.njfu.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njfu.surveypark.dao.BaseDao;
import com.njfu.surveypark.model.Survey;
import com.njfu.surveypark.model.User;
import com.njfu.surveypark.service.PaginationService;
@Service("paginationService")
public class PaginationServiceImpl implements PaginationService {

	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;

	@SuppressWarnings("unused")
	@Override
	public List<Survey> paging(User user, int pageNow, int pageSize) {
		int userid = user.getId();
		String hql = "from Survey s where s.user.id = "+userid+" ";
		List<Survey> list = surveyDao.queryForPage(hql, pageNow, pageSize);
		return list;
	}

	@Override
	public int getTotalSurvey(User user) {
		String hql = "from Survey s where s.user.id = ?";
		return surveyDao.findEntityByHQL(hql,user.getId()).size();
	}

	@Override
	public int getTotalSurvey(User user, int survey_status) {
		if(survey_status!=3){
			//不关闭
			if(survey_status==0){
				System.out.println(survey_status);
				String hql = "from Survey s where s.user.id = ? and s.closed= ?";
				return surveyDao.findEntityByHQL(hql,user.getId(),true).size();	
			}
			else{
				//关闭
				String hql = "from Survey s where s.user.id = ? and s.closed= ?";
				return surveyDao.findEntityByHQL(hql,user.getId(),false).size();

			}
			
		}
		else{
			String hql = "from Survey s where s.user.id = ?";
			return surveyDao.findEntityByHQL(hql,user.getId()).size();	
		}
	}

	@SuppressWarnings("unused")
	@Override
	public List<Survey> paging(User user, int survey_status, int pageNow,
			int pageSize) {
		int userid = user.getId();
		
		if(survey_status!=3){
			//不关闭
			if(survey_status==0){
				System.out.println(survey_status);
				String hql = "from Survey s where s.user.id = userid and s.closed=true";
				List<Survey> list = surveyDao.queryForPage(hql, pageNow, pageSize);
				return list;	
			}
			else{
				//关闭
				System.out.println(survey_status);
				String hql = "from Survey s where s.user.id = userid and s.closed=false";
				List<Survey> list = surveyDao.queryForPage(hql, pageNow, pageSize);
				return list;	
			}
		}
		else{

			String hql = "from Survey s where s.user.id = userid";
			List<Survey> list = surveyDao.queryForPage(hql, pageNow, pageSize);
			return list;	
		}
	}

	@Override
	public int getTotalSurvey(User user, String survey_name) {
		String hql = "from Survey s where s.user.id = ? and s.title like ?";
		return surveyDao.findEntityByHQL(hql,user.getId(),"%" + survey_name + "%").size();	
	}

	@Override
	public List<Survey> paging(User user, String survey_name, int pageNow,
			int pageSize) {
		String hql = "from Survey s where s.user.id = userid and s.title like '%"+survey_name+"%'";
		List<Survey> list = surveyDao.queryForPage(hql, pageNow, pageSize);
		return list;	
	}

	
	@Override
	public int getTotalSurvey() {
		String hql = "from Survey s where s.closed = ? ";
		return surveyDao.findEntityByHQL(hql,false).size();
	}

	@Override
	public List<Survey> paging(int pageNow, int pageSize) {
		String hql = "from Survey s where s.closed = false ";
		List<Survey> list = surveyDao.queryForPage(hql, pageNow, pageSize);
		return list;
	}

	@Override
	public int getTotalSurvey(String survey_name) {
		String hql = "from Survey s where s.title like ? and s.closed = ? ";
		return surveyDao.findEntityByHQL(hql,"%" + survey_name + "%",false).size();
	}

	@Override
	public List<Survey> paging(String survey_name, int pageNow, int pageSize) {
		String hql = "from Survey s where s.closed = false and s.title like '%"+survey_name+"%'";
		List<Survey> list = surveyDao.queryForPage(hql, pageNow, pageSize);
		return list;	
	}

}
