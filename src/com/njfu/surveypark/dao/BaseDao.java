package com.njfu.surveypark.dao;

import java.util.List;

/**
 * BaseDao接口
 * @author CK
 *
 * @param <T>
 */
public interface BaseDao<T> {
	//写操作
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	public void batchEntityByHQL(String hql,Object...objects);
	
	//读操作
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql,Object...objects);
	
	
	//单值检索,确保查询结果有且只有一条记录
	public Object uniqueResult(String hql,Object...objects);
	
	//利用hibernate分页查询
	public List<T> queryForPage(String hql, int pageNow,int pageSize);
	//执行原生的sql语句
	public void executeSQL(String sql, Object[] objects);
	@SuppressWarnings("rawtypes")
	public List executeSQLQuery(Class clazz, String sql, Object[] objects);


	
}
