package com.njfu.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.security.Right;
import com.njfu.surveypark.model.security.Role;
import com.njfu.surveypark.service.RightService;
import com.njfu.surveypark.service.RoleService;
import com.njfu.surveypark.struts2.action.BaseAction;
/**
 * ��ɫAction
 * @author CK
 * 2015��3��18������3:01:24
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = -3270697573788462556L;
	
	@Resource
	private RoleService roleService ;
	@Resource
	private RightService rightService ;
	
	private List<Role> allRoles ; //���н�ɫ����
	private List<Right> noOwnRights ;//��ɫû�е�Ȩ�޼���
	private Integer roleId ;//��ɫID
	private Integer[] ownRightIds ;	//��ɫӵ�е�Ȩ��id����
	public List<Role> getAllRoles() {
		return allRoles;
	}
	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}
	public List<Right> getNoOwnRights() {
		return noOwnRights;
	}
	public void setNoOwnRights(List<Right> noOwnRights) {
		this.noOwnRights = noOwnRights;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer[] getOwnRightIds() {
		return ownRightIds;
	}
	public void setOwnRightIds(Integer[] ownRightIds) {
		this.ownRightIds = ownRightIds;
	}
	
	/**
	 * ��ѯ���н�ɫ��Ϣ�����ؽ�ɫlist ����
	 * @return
	 */
	public String findAllRoles(){
		this.allRoles = roleService.findAllEntities();
		return "roleListPage" ;
	}
	
	/**
	 * �������ӽ�ɫҳ��
	 * @return
	 */
	public String toAddRolePage(){
		this.noOwnRights = rightService.findAllEntities();
		return "addRolePage" ;
	}
	
	/**
	 * ������½�ɫ
	 * @return
	 */
	public String saveOrUpdateRole(){
		roleService.saveOrUpdateRole(model,ownRightIds);
		return "findAllRolesAction" ;
	}
	
	/**
	 * �༭��ɫ
	 * @return
	 */
	public String editRole(){
		this.model = roleService.getEntity(roleId);
		this.noOwnRights = rightService.findRightsNotInRange(model.getRights());
		return "editRolePage" ;
	}
	
	/**
	 * ɾ����ɫ
	 * @return
	 */
	public String deleteRole(){
		Role r = new Role();
		r.setId(roleId);
		roleService.deleteEntity(r);
		return "findAllRolesAction" ;
	}
	
}
