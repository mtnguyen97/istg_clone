package com.example.istg.dto;

import com.example.istg.commons.User;

public class IdAndCreatedAtAndCreatedBy extends IdAndCreatedAt {

	User createdBy;

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
