package org.hwi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	@GetMapping("/sample1")
	public void sample1(Model model) {
		//model.addAttribute("greeting", "Hello Spring Boot!");
		model.addAttribute("greeting", "Hello 피똥");
	}
}
