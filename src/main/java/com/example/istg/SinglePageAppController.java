package com.example.istg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SinglePageAppController {
	
	@RequestMapping(method = RequestMethod.GET, value = {"explore", "message"})
	public String dispatchView() {
		return "index";
	}

}
