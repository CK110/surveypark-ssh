package com.njfu.surveypark.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njfu.surveypark.dao.BaseDao;
import com.njfu.surveypark.model.security.Right;
import com.njfu.surveypark.service.RightService;
import com.njfu.surveypark.util.DataUtil;
import com.njfu.surveypark.util.StringUtil;
import com.njfu.surveypark.util.ValidateUtil;
@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements RightService {

	@Resource(name="rightDao")
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}

	@Override
	public void saveOrUpdateRight(Right r) {
		//插入操作
		int pos = 0 ;
		long code = 1L ; 
		if(r.getId() == null){
			String hql = "select max(r.rightPos),max(r.rightCode) from Right r "
					+ "where r.rightPos = (select max(rr.rightPos) from Right rr)" ;
			Object[] arr = (Object[]) this.uniqueResult(hql);
			Integer topPos = (Integer) arr[0];
			Long topCode = (Long) arr[1];
			//没有权限
			if(topPos == null){
				pos = 0 ;
				code = 1L ;
			}
			else{
				//权限码是否到达最大值
				if(topCode >= (1L << 60)){
					pos = topPos + 1 ;
					code = 1L ;
				}
				else{
					pos = topPos ;
					code = topCode << 1 ;
				}
			}
			
			r.setRightPos(pos);
			r.setRightCode(code);
		}
		this.saveOrUpdateEntity(r);
	}
	
	@Override
	public void appendRightByURL(String url) {
		String hql = "select count(*) from Right r where r.rightUrl = ?" ;
		Long count = (Long) this.uniqueResult(hql,url);
		if(count == 0){
			Right r = new Right();
			r.setRightUrl(url);
			this.saveOrUpdateRight(r);
		}
	}

	@Override
	public void batchUpdateRights(List<Right> allRights) {
		String hql = "update Right r set r.rightName = ? where r.id = ?" ;
		if(ValidateUtil.isValid(allRights)){
			for(Right r  : allRights){
				this.batchEntityByHQL(hql,r.getRightName(),r.getId());
			}
		}
	}

	@Override
	public List<Right> findRightsInRange(Integer[] ids) {
		if(ValidateUtil.isValid(ids)){
			String hql = "from Right r where r.id in ("+StringUtil.arr2Str(ids)+")" ;
			return this.findEntityByHQL(hql);
		}
		return null ;
	}

	@Override
	public List<Right> findRightsNotInRange(Set<Right> rights){
		if(!ValidateUtil.isValid(rights)){
			return this.findAllEntities();
		}
		else{
			String hql = "from Right r where r.id not in("+DataUtil.extractRightIds(rights)+")" ;
			return this.findEntityByHQL(hql);
		}
	}

	@Override
	public int getMaxRightPos() {
		String hql = "select max(r.rightPos) from Right r" ;
		Integer pos = (Integer) this.uniqueResult(hql);
		return pos == null ? 0 : pos ;
	}

}
