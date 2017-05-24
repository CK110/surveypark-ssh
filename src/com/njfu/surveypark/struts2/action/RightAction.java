package com.njfu.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njfu.surveypark.model.security.Right;
import com.njfu.surveypark.service.RightService;
import com.njfu.surveypark.struts2.action.BaseAction;
/**
 * 权限Action
 * @author CK
 * 2015年3月11日下午2:43:45
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
	 * 查询所有权限
	 * @return
	 */
	public String findAllRights(){
		this.allRights = rightService.findAllEntities();
		return "rightListPage" ;
	} 

	/**
	 * 跳转到增加权限界面
	 * @return
	 */
	public String toAddRightPage(){
		return "addRightPage" ;
	}

	/**
	 * 保存/更新权限
	 * @return
	 */
	public String saveOrUpdateRight(){
		rightService.saveOrUpdateRight(model);
		return "findAllRightAction" ;
	}
	
	/**
	 * 编辑权限
	 * @return
	 */
	public String editRight(){
		this.model = rightService.getEntity(rightId);
		return "editRightPage" ;	
	}
	
	/**
	 * 删除权限
	 * @return
	 */
	public String deleteRight(){
		Right r = new Right();
		r.setId(rightId);
		rightService.deleteEntity(r);
		return "findAllRightAction" ;
	}
	
	/**
	 * 批量更新权限 
	 * @return
	 */
	public String batchUpdateRights(){
		rightService.batchUpdateRights(allRights);
		return "findAllRightAction" ;	
	}
	
}
