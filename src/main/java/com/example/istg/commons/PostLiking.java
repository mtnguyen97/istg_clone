package com.example.istg.commons;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Reference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostLiking implements Serializable {

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	@ManyToOne(cascade = CascadeType.PERSIST, optional = false)
	private User user;
	@ManyToOne(cascade = CascadeType.PERSIST, optional = false, fetch = FetchType.LAZY)
	@JsonIgnore
	private Post post;
	private Date likingAt;
	private Date unlikingAt;

}
