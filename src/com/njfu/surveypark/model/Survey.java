package com.njfu.surveypark.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 调查类
 */
public class Survey extends BaseEntity {
	private static final long serialVersionUID = 6761156994601350637L;
	private Integer id;
	private String title = "未命名";
	private String preText = "上一步";
	private String nextText = "下一步";
	private String exitText = "退出";
	private String doneText = "完成";
	private Date createTime = new Date();	
	private boolean closed;//是否关闭
	private float minOrderno;	//最小页序
	private float maxOrderno ;	//最大页序	
	//建立从Survey到User之间多对一关联关系
	private User user ;
	//问卷调查Logo
	private String logoPhotoPath ;
	//建立从Survey到Page之间一对多关联关系
	private Set<Page> pages = new HashSet<Page>();
	
	
	public float getMinOrderno() {
		return minOrderno;
	}

	public void setMinOrderno(float minOrderno) {
		this.minOrderno = minOrderno;
	}

	public float getMaxOrderno() {
		return maxOrderno;
	}

	public void setMaxOrderno(float maxOrderno) {
		this.maxOrderno = maxOrderno;
	}
	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreText() {
		return preText;
	}

	public void setPreText(String preText) {
		this.preText = preText;
	}

	public String getNextText() {
		return nextText;
	}

	public void setNextText(String nextText) {
		this.nextText = nextText;
	}

	public String getExitText() {
		return exitText;
	}

	public void setExitText(String exitText) {
		this.exitText = exitText;
	}

	public String getDoneText() {
		return doneText;
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getLogoPhotoPath() {
		return logoPhotoPath;
	}

	public void setLogoPhotoPath(String logoPhotoPath) {
		this.logoPhotoPath = logoPhotoPath;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Page> getPages() {
		return pages;
	}

	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}	
	
	

}
