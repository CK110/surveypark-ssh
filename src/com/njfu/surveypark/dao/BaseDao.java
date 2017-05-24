package com.njfu.surveypark.dao;

import java.util.List;

/**
 * BaseDao�ӿ�
 * @author CK
 *
 * @param <T>
 */
public interface BaseDao<T> {
	//д����
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	public void batchEntityByHQL(String hql,Object...objects);
	
	//������
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql,Object...objects);
	
	
	//��ֵ����,ȷ����ѯ�������ֻ��һ����¼
	public Object uniqueResult(String hql,Object...objects);
	
	//����hibernate��ҳ��ѯ
	public List<T> queryForPage(String hql, int pageNow,int pageSize);
	//ִ��ԭ����sql���
	public void executeSQL(String sql, Object[] objects);
	@SuppressWarnings("rawtypes")
	public List executeSQLQuery(Class clazz, String sql, Object[] objects);


	
}
