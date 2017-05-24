package com.njfu.surveypark.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njfu.surveypark.dao.BaseDao;
import com.njfu.surveypark.model.security.Right;
import com.njfu.surveypark.model.security.Role;
import com.njfu.surveypark.service.RightService;
import com.njfu.surveypark.service.RoleService;
import com.njfu.surveypark.util.DataUtil;
import com.njfu.surveypark.util.StringUtil;
import com.njfu.surveypark.util.ValidateUtil;
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	
	@Resource(name="roleDao")
	public void setDao(BaseDao<Role> dao) {
		super.setDao(dao);
	}
	@Resource
	private RightService rightService ;
	
	@Override
	public void saveOrUpdateRole(Role model, Integer[] ids) {
		//没有给角色授予任何权限
		if(!ValidateUtil.isValid(ids)){
			model.getRights().clear();
		}
		else{
			List<Right> rights = rightService.findRightsInRange(ids);
			model.setRights(new HashSet<Right>(rights));
		}
		this.saveOrUpdateEntity(model);
	}

	@Override
	public List<Role> findRolesNotInRange(Set<Role> roles) {
		if(!ValidateUtil.isValid(roles)){
			return this.findAllEntities();
		}
		else{
			String hql = "from Role r where r.id not in("+DataUtil.extractRightIds(roles)+")" ;
			return this.findEntityByHQL(hql);
		}
	}

	@Override
	public List<Role> findRolesInRange(Integer[] ownRoleIds) {
		if(ValidateUtil.isValid(ownRoleIds)){
			String hql = "from Role r where r.id in ("+StringUtil.arr2Str(ownRoleIds)+")" ;
			return this.findEntityByHQL(hql);
		}
		return null ;
	}

}
