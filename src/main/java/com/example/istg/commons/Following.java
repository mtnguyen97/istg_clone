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
public class Following implements Serializable {
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	private User follower;
	private User followee;
	private Date followingAt;
	private Date unfollowingAt;
	private boolean acceptedAt;
}
