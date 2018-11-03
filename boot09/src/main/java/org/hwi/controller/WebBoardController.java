package org.hwi.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.hwi.domain.WebBoard;
import org.hwi.persistence.CustomCrudRepository;
import org.hwi.persistence.WebBoardRepository;
import org.hwi.vo.PageMaker;
import org.hwi.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

	
	@Autowired
	//private WebBoardRepository repo;
	private CustomCrudRepository repo;
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo")WebBoard vo ){
		log.info("register get");
		vo.setTitle("샘플 게시물 제목입니다....");
		vo.setContent("내용을 처리해 봅니다 " );
		vo.setWriter("user00");
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo")WebBoard vo, RedirectAttributes rttr){
		
		log.info("register post");
		log.info("" + vo);

		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/boards/list";
	}
	
	@GetMapping("/view")
	public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model){
		
		log.info("BNO: "+ bno);
		
		repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
		
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@GetMapping("/modify")
	public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model){
		
		log.info("MODIFY BNO: "+ bno);
		
		repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@PostMapping("/modify")
	public String modifyPost(WebBoard board, PageVO vo, RedirectAttributes rttr ){
		
		log.info("Modify WebBoard: " + board);
		
		
		repo.findById(board.getBno()).ifPresent( origin ->{
		 
			origin.setTitle(board.getTitle());
			origin.setContent(board.getContent());
			
			repo.save(origin);
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("bno", origin.getBno());
		});
		
		//페이징과 검색했던 결과로 이동하는 경우 
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/boards/view";
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@PostMapping("/delete")
	public String delete(Long bno, PageVO vo, RedirectAttributes rttr ){
		
		log.info("DELETE BNO: " + bno);
		
		repo.deleteById(bno);
		
		rttr.addFlashAttribute("msg", "success");

		//페이징과 검색했던 결과로 이동하는 경우 
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/boards/list";
	}
	
	
	@GetMapping("/list")
	public void list(@ModelAttribute("pageVO") PageVO vo, Model model){
		
		Pageable page = vo.makePageable(0, "bno");
		
		Page<Object[]> result = repo.getCustomPage(vo.getType(), 
				vo.getKeyword(), page);
		
		log.info(""+ page);
		log.info(""+result);
		
		log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());
		
		
		model.addAttribute("result", new PageMaker<>(result));
				
	}

}



//ver 1
//@GetMapping("/list")
//public void list(
//		@PageableDefault(
//				direction=Sort.Direction.DESC, 
//				sort="bno" , 
//				size=10 , 
//				page = 0) Pageable page , Model model ) {
//
//	log.info("list() called..." + page);
//	
//	Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);
//
//	PageMaker<WebBoard> pgMaker = new PageMaker<>(result.getContent(), 
//			result.getTotalPages(), 
//			result.getPageable());
//	
//	
//	log.info(""+pgMaker);
//	
//	model.addAttribute("result", pgMaker);
//
//}

//ver 2
//@GetMapping("/list")
//public void list(PageVO vo, Model model){
//	
//	Pageable page = vo.makePageable(0, "bno");
//	
//	Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);
//	
//	log.info(""+ page);
//	log.info(""+result);
//	
//	log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());
//	
//	
//	model.addAttribute("result", new PageMaker(result));
//			
//}


