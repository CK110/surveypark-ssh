package com.njfu.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.security.Right;
import com.njfu.surveypark.service.RightService;
import com.njfu.surveypark.struts2.action.BaseAction;
/**
 * Ȩ��Action
 * @author CK
 * 2015��3��11������2:43:45
 */
@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right> {

	private static final long serialVersionUID = -1347624609379165355L;
	
	@Resource
	private RightService rightService ;
	
	private List<Right> allRights;
	private Integer rightId ;
	
	
	public Integer getRightId() {
		return rightId;
	}

	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}

	public List<Right> getAllRights() {
		return allRights;
	}

	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}

	/**
	 * ��ѯ����Ȩ��
	 * @return
	 */
	public String findAllRights(){
		this.allRights = rightService.findAllEntities();
		return "rightListPage" ;
	} 

	/**
	 * ��ת������Ȩ�޽���
	 * @return
	 */
	public String toAddRightPage(){
		return "addRightPage" ;
	}

	/**
	 * ����/����Ȩ��
	 * @return
	 */
	public String saveOrUpdateRight(){
		rightService.saveOrUpdateRight(model);
		return "findAllRightAction" ;
	}
	
	/**
	 * �༭Ȩ��
	 * @return
	 */
	public String editRight(){
		this.model = rightService.getEntity(rightId);
		return "editRightPage" ;	
	}
	
	/**
	 * ɾ��Ȩ��
	 * @return
	 */
	public String deleteRight(){
		Right r = new Right();
		r.setId(rightId);
		rightService.deleteEntity(r);
		return "findAllRightAction" ;
	}
	
	/**
	 * ��������Ȩ�� 
	 * @return
	 */
	public String batchUpdateRights(){
		rightService.batchUpdateRights(allRights);
		return "findAllRightAction" ;	
	}
	
}
