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
public class User implements Serializable {
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	private String username;
	private String displayName;
	private String bio;
	private String email;
	private String phone;
	private int gender;
	private Date createdAt;
	private Date deletedAt;
	private boolean activated;
	private String avatar;
	private boolean privateUser;
	private String password;

}
