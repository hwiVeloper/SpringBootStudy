package org.hwi.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@Log
public class SampleController {
	@GetMapping("/")
	public String index() {
		log.info("index");
		return "index";
	}
	
	@RequestMapping("/guest")
	public void forGuest() {
		log.info("guest");
	}
	
	@RequestMapping("/manager")
	public void forManager() {
		log.info("manager");
	}
	
	@RequestMapping("/admin")
	public void forAdmin() {
		log.info("admin");
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping("/adminSecret")
	public void forAdminSecret() {
		log.info("admin secret");
	}
	
	@Secured({"ROLE_MANAGER"})
	@RequestMapping("/managerSecret")
	public void forManagerSecret() {
		log.info("manager secret");
	}
}
