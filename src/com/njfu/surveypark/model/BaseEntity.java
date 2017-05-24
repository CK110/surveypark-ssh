package com.njfu.surveypark.model;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 231202274506291103L;

	public abstract Integer getId();

	public abstract void setId(Integer id) ;

}
