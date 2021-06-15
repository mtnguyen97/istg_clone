package com.example.istg.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdAndCreatedAtAndUpdatedAt extends IdAndCreatedAt {
	@DateTimeFormat(iso = ISO.DATE_TIME)
	Date updatedAt;
}
