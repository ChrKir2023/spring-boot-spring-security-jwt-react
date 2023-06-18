package com.reactapp.springjwt.payload.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class RouteRequest implements Serializable{
	
	@NotBlank
	private String routename;
	
	private int userid;

	private String category;
	
	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	

}
