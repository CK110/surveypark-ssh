package com.njfu.surveypark.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.njfu.surveypark.dao.BaseDao;
import com.njfu.surveypark.model.User;
import com.njfu.surveypark.model.security.Role;
import com.njfu.surveypark.service.RoleService;
import com.njfu.surveypark.service.UserService;
import com.njfu.surveypark.util.ValidateUtil;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
	UserService {
	@Resource
	private RoleService roleService ;
	/**
	 * 重写该方法,目的是为了覆盖超类中该方法的注解,指明注入指定的Dao对象,否则spring
	 * 无法确定注入哪个Dao---有四个满足条件的Dao.
	 */
	@Resource(name="userDao")
	public void setDao(BaseDao<User> dao) {
		super.setDao(dao);
	}

	/**
	 *  判断email是否占用
	 */
	@Override
	public boolean isRegisted(String email) {
		String hql = "from User u where u.email = ?" ;
		List<User> list = this.findEntityByHQL(hql, email);
		return ValidateUtil.isValid(list) ;
	}

	/**
	 * 验证登陆信息
	 */
	@Override
	public User validateLoginInfo(String email, String md5) {
		String hql = "from User u where u.email = ? and u.password = ?" ;
		List<User> list = this.findEntityByHQL(hql,email,md5);
		return ValidateUtil.isValid(list)?list.get(0):null;
	}

	/**
	 * 更新用户授权(只能更新角色设置)
	 */
	@Override
	public void updateAuthorize(User model, Integer[] ownRoleIds) {
		//查询新对象,不可以对原有对象操纵
		User newUser = this.getEntity(model.getId());
		if(!ValidateUtil.isValid(ownRoleIds)){
			newUser.getRoles().clear();
		}
		else{
			List<Role> roles = roleService.findRolesInRange(ownRoleIds);
			newUser.setRoles(new HashSet<Role>(roles));
		}
		
	}
	
	/**
	 * 清除授权
	 */
	@Override
	public void clearAuthorize(Integer userId){
		this.getEntity(userId).getRoles().clear();
	}
}
