package org.hwi.controller;

import org.hwi.domain.SampleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}
	
	@GetMapping("/sample")
	public SampleVO makeSample() {
		SampleVO vo = new SampleVO();
		
		vo.setVal1("v1");
		vo.setVal2("v2");
		vo.setVal3("v3");
		
		System.out.println(vo);
		
		return vo;
	}
}
