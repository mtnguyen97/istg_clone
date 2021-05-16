package com.example.istg.commons;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private User sender;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private User receiver;
	private String content;
	private Date createdAt;
	private Date deliveredAt;
	private Date seenAt;
	private Date deletedAt;
}
