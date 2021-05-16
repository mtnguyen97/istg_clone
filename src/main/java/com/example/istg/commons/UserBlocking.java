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
public class UserBlocking {
	private @Id  @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private User blocker;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private User blockee;
	private Date blockingAt;
	private Date unblockingAt;
}
