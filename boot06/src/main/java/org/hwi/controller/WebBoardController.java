package org.hwi.controller;

import org.hwi.domain.WebBoard;
import org.hwi.persistence.WebBoardRepository;
import org.hwi.vo.PageMaker;
import org.hwi.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {
	@Autowired
	WebBoardRepository repo;
	
	//@GetMapping("/list")
	public void list(@PageableDefault(direction=Sort.Direction.DESC, sort="bno", size=10, page=0) Pageable page) {
		log.info("list() called....." + page);
	}
	
	@GetMapping("/list")
	public void list1(PageVO vo, Model model) {
		Pageable page = vo.makePageable(0, "bno");
		
		Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);
		
		log.info("" + page);
		log.info("" + result);
		
		log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());
		
		model.addAttribute("result", new PageMaker(result));
	}
}
