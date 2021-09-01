package com.example.istg.commons;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Entity(name = "chatcreds")
@Getter
@Setter
@ToString
public class ChatCred implements Serializable {

	private @Id Long id;
	private String username, secret;

}
