package com.njfu.surveypark.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import com.njfu.surveypark.dao.BaseDao;
import com.njfu.surveypark.service.BaseService;

/**
 * 抽象的BaseService,专门用于继承 
 * @author CK
 *
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	private BaseDao<T> dao ;
	
	private Class<T> clazz ;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	//注入dao，字段不能覆盖，方法能覆盖
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		dao.batchEntityByHQL(hql, objects);
	}

	@Override
	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	@Override
	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}
	
	@Override
	public List<T> findAllEntities(){
		String hql = "from " + clazz.getSimpleName() ;
		return this.findEntityByHQL(hql);
	}
	
	@Override
	public Object uniqueResult(String hql,Object...objects){
		return dao.uniqueResult(hql, objects);
	}

	@Override
	public void executeSQL(String sql, Object... objects) {
		dao.executeSQL(sql, objects);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List executeSQLQuery(Class clazz, String sql, Object... objects) {
		return dao.executeSQLQuery(clazz,sql, objects);
	}
	

}
