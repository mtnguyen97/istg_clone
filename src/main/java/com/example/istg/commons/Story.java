package com.example.istg.commons;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Story implements Serializable {
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	private String image;
	private User postedBy;
	private Date createdAt;
	private Date deletedAt;
	private boolean publicStory;
}
