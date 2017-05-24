package com.njfu.surveypark.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.njfu.surveypark.dao.BaseDao;

/**
 * 抽象的dao实现,专门用于继承
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	//注入sessionFactory
	@Resource
	private SessionFactory sf ;
	
	private Class<T> clazz ;
	
	
	public BaseDaoImpl(){
		//得到泛型话超类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	@Override
	public void saveEntity(T t) {
		sf.getCurrentSession().save(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		sf.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void updateEntity(T t) {
		sf.getCurrentSession().update(t);
	}

	@Override
	public void deleteEntity(T t) {
		sf.getCurrentSession().delete(t);
	}
	
	/**
	 * 按照HQL语句进行批量更新
	 */
	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		Query q = sf.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < objects.length ; i ++){
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}

	@Override
	public T loadEntity(Integer id) {
		return (T) sf.getCurrentSession().load(clazz, id);
	}

	@Override
	public T getEntity(Integer id) {
		return (T) sf.getCurrentSession().get(clazz, id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		Query q = sf.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < objects.length ; i ++){
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

	//单值检索,确保查询结果有且只有一条记录
	public Object uniqueResult(String hql,Object...objects){
		Query q = sf.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < objects.length ; i ++){
			q.setParameter(i, objects[i]);
		}
		return q.uniqueResult();
		
	}
	
	//分页查询
	@Override
	public List<T> queryForPage(String hql, int pageNow,int pageSize){
		Query q = sf.getCurrentSession().createQuery(hql);
		q.setFirstResult(pageSize * (pageNow - 1));  
        q.setMaxResults(pageSize); 
		return q.list() ;
	}

	//执行原生的sql语句
    @Override
	public void executeSQL(String sql, Object[] objects) {
		SQLQuery q = sf.getCurrentSession().createSQLQuery(sql);
		for(int i = 0 ; i < objects.length ; i ++){
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}

	//执行原生的sql查询
	@SuppressWarnings("rawtypes")
	@Override
	public List executeSQLQuery(Class clazz, String sql, Object[] objects) {
		SQLQuery q = sf.getCurrentSession().createSQLQuery(sql);
		//添加实体类
		if(clazz != null){
			q.addEntity(clazz);
		}
		for(int i = 0 ; i < objects.length ; i ++){
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}
	
	
}
