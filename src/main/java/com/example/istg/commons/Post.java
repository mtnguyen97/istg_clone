package com.example.istg.commons;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class Post implements Serializable {
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	private String[] images;
	private String caption;
	private Date createdAt;
	private Date deletedAt;
	private User postedBy;
	private boolean publicPost;

}
