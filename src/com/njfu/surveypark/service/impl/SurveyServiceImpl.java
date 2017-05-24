package com.njfu.surveypark.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njfu.surveypark.dao.BaseDao;
import com.njfu.surveypark.model.Answer;
import com.njfu.surveypark.model.Page;
import com.njfu.surveypark.model.Question;
import com.njfu.surveypark.model.Survey;
import com.njfu.surveypark.model.User;
import com.njfu.surveypark.service.SurveyService;
/**
 * SurveyService实现
 */
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {
	
	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;
	
	@Resource(name="pageDao")
	private BaseDao<Page> pageDao;
	
	@Resource(name="questionDao")
	private BaseDao<Question> questionDao;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao;

	@Override
	public List<Survey> findMySurveys(User user) {
		String hql = "from Survey s where s.user.id = ? order by s.createtime asc";
		return surveyDao.findEntityByHQL(hql,user.getId());
	}

	@Override
	public Survey newSurvey(User user) {
		Survey s = new Survey();
		s.setClosed(true);
		s.setUser(user);
		Page p = new Page();
		p.setSurvey(s);
		s.getPages().add(p);
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s;
	}

	@Override
	public Survey getSurvey(Integer sid) {
		//System.out.println(sid);
		return surveyDao.getEntity(sid);
	}

	@Override
	public Survey getSurveyWithChildren(Integer sid) {
		Survey s = this.getSurvey(sid);
		//强行初始化pages和questions集合
		for(Page p : s.getPages()){
			p.getQuestions().size();
		}
		return s;
	}
	
	@Override
	public void updateSurvey(Survey model) {
		surveyDao.updateEntity(model);
	}

	@Override
	public void saveOrUpdatePage(Page model) {
		pageDao.saveOrUpdateEntity(model);
	}

	@Override
	public Page getPage(Integer pid) {
		return pageDao.getEntity(pid);
	}

	@Override
	public void saveOrUpdateQuestion(Question model) {
		questionDao.saveOrUpdateEntity(model);
	}

	@Override
	public void deleteQuestion(Integer qid) {
		
		String hql="delete from Answer a where a.questionId = ?";
		answerDao.batchEntityByHQL(hql,qid);
		
	    hql="delete from Question q where q.id= ?";
		questionDao.batchEntityByHQL(hql,qid);
		
		
	}
	
	@Override
	public void deletePage(Integer pid) {
		
		//delete answer
		String hql = "delete from Answer a where a.questionId in (select q.id from Question q where q.page.id = ?)" ;
		answerDao.batchEntityByHQL(hql,pid);
		//delete questions
		hql = "delete from Question q where q.page.id = ?" ;
		questionDao.batchEntityByHQL(hql,pid);
		//delete page
		hql = "delete from Page p where p.id = ?" ;
		pageDao.batchEntityByHQL(hql,pid);

	}

	@Override
	public void deleteSurvey(Integer sid) {
		//delete answers
		String hql = "delete from Answer a where a.surveyId = ?" ;
		answerDao.batchEntityByHQL(hql,sid);		
		//hibernate在写操作中,不允许两级以上的链接.
		//hql = "delete from Question q where q.page.survey.id = ?" ;
		hql = "delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)" ;
		questionDao.batchEntityByHQL(hql, sid);
		
		//delete page
		hql = "delete from Page p where p.survey.id = ? " ;
		pageDao.batchEntityByHQL(hql, sid);
		
		//delete survey
		hql = "delete from Survey s where s.id = ?" ;
		surveyDao.batchEntityByHQL(hql, sid);
		
	}

	@Override
	public Question getQuestion(Integer qid) {
		// TODO Auto-generated method stub
		return questionDao.getEntity(qid);
	}

	@Override
	public void clearAnswers(Integer sid) {
		String hql = "delete from Answer a where a.surveyId = ?";
		answerDao.batchEntityByHQL(hql, sid);
	}

	@Override
	public void toggleStatus(Integer sid) {
		Survey s = this.getSurvey(sid);
		String hql = "update Survey s set s.closed = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql,!s.isClosed(),sid);
	}

	/**
	 * 查询指定页面的上一页
	 */
	private Page getPrePage(Page targPage) {
		String hql = "from Page p where p.survey.id = ? and p.orderno < ? order by p.orderno desc" ;
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}
	
	/**
	 * 查询指定页面的下一页
	 */
	private Page getNextPage(Page targPage) {
		String hql = "from Page p where p.survey.id = ? and p.orderno > ? order by p.orderno asc" ;
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}
	
	@Override
	public List<Survey> findAllAvailableSurveys() {
		String hql = "from Survey s where s.closed = ? ";
		return surveyDao.findEntityByHQL(hql,false);
	}

	@Override
	public Page getFirstPage(Integer sid) {
		String hql = "from Page p where p.survey.id = ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql,sid);
		Page p = list.get(0);
		p.getQuestions().size();
		p.getSurvey().getTitle();
		return p ;
	}

	@Override
	public Page getPrePage(Integer currPid) {
		Page p = this.getPage(currPid);
		p = this.getPrePage(p);//防止懒加载
		p.getQuestions().size();
		return p ;
	}

	@Override
	public Page getNextPage(Integer currPid) {
		Page p = this.getPage(currPid);
		p = this.getNextPage(p);//防止懒加载
		p.getQuestions().size();
		return p ;
	}

	@Override
	public void saveAnswers(List<Answer> processAnswers) {
		Date date = new Date();
		String uuid = UUID.randomUUID().toString();
		for(Answer a : processAnswers){
			a.setUuid(uuid);
			a.setAnswerTime(date);
			answerDao.saveEntity(a);
		}
	}

	@Override
	public List<Question> getQuestions(Integer sid) {
		String hql = "from Question q where q.page.survey.id = ?" ;
		return questionDao.findEntityByHQL(hql, sid);
	}

	@Override
	public List<Answer> getAnswers(Integer sid) {
		String hql = "from Answer a where a.surveyId = ? order by a.uuid asc" ;
		return answerDao.findEntityByHQL(hql,sid);
	}

}

	

