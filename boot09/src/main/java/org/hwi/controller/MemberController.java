package org.hwi.controller;

import org.hwi.domain.Member;
import org.hwi.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	PasswordEncoder pwEncoder;
	
	@Autowired
	MemberRepository repo;
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@Transactional
	@PostMapping("/join")
	public String joinPost(@ModelAttribute("member") Member member) {
		log.info("MEMBER: " + member);
		
		String encryptPw = pwEncoder.encode(member.getUpw());
		
		log.info("en: " + encryptPw);
		
		member.setUpw(encryptPw);
		
		repo.save(member);
		
		return "/member/joinResult";
	}
}
