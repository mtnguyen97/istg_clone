package com.example.istg.commons;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.sun.istack.NotNull;

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
	@NotBlank(message = "invalid_username")
	@Email(message = "invalid_username")
	@Length(max = 256, message = "username_too_long")
	@Column(unique = true)
	private String username;
	@Length(max = 60, message = "display_name_too_long")
	private String displayName;
	@Length(max = 200, message = "bio_too_long")
	private String bio;
	@NotBlank(message = "invalid_email")
	@Email(message = "invalid_email")
	@Length(max = 256, message = "email_too_long")
	@Column(unique = true)
	private String email;
	@NotBlank(message = "invalid_phone")
	private String phone;
	private int gender;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdAt;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date deletedAt;
	private boolean activated;
	private String avatar;
	private boolean privateUser;
	@NotBlank
	@Pattern(regexp = "^[a-fA-F0-9]{64}$", message = "invalid_password")
	private String password;

}
