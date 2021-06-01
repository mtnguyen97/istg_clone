package com.example.istg.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdAndCreatedAtAndCreatedBy extends IdAndCreatedAt {

	Long createdBy;

}
