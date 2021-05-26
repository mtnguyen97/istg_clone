package com.example.istg;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.istg.services.UserService;

@RestController
@RequestMapping("/nonauth/api")
public class NonAuthController {
	@Autowired
	private UserService service;

	@GetMapping("/emailexisted")
	public Map<String, Object> checkUsableEmail(@RequestParam("email") String email) {
		Map<String, Object> response = new HashMap<>();
		response.put("email", email);
		response.put("existed", service.isEmailExisted(email));
		return response;
	}

}
