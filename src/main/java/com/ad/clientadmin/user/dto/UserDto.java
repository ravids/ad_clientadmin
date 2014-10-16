package com.ad.clientadmin.user.dto;

import java.lang.Integer;import java.lang.String; /**
 * Specialized person data for REST layer.
 * 
 * @author RD
 */
public class UserDto {

	private Integer id;
	private String fullname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
