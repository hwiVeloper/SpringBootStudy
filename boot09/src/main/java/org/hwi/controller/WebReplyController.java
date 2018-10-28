package org.hwi.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.hwi.domain.WebBoard;
import org.hwi.domain.WebReply;
import org.hwi.persistence.WebBoardRepository;
import org.hwi.persistence.WebReplyRepository;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/replies/*")
@Log
public class WebReplyController {

	@Autowired
	private WebReplyRepository replyRepo;
	
	@Autowired
	private WebBoardRepository boardRepo;
	
	@GetMapping("/{bno}")
	public ResponseEntity<List<WebReply>> getReplies(
			@PathVariable("bno")Long bno){
	
		log.info("get All Replies..........................");
		
		WebBoard board = new WebBoard();
		board.setBno(bno);
		return new ResponseEntity<>(getListByBoard(board),HttpStatus.OK );
	}
	
	@Transactional
	@PostMapping("/{bno}")
	public ResponseEntity<List<WebReply>> addReply(
			@PathVariable("bno")Long bno, 
			@RequestBody WebReply reply){

		log.info("addReply..........................");
		log.info("BNO: " + bno);
		log.info("REPLY: " + reply);
		
		WebBoard board = new WebBoard();
		board.setBno(bno);
		
		reply.setBoard(board);
		
		replyRepo.save(reply);		
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
		
	}
	
	@Transactional
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<WebReply>> remove(
			@PathVariable("bno")Long bno,
			@PathVariable("rno")Long rno){
		
		log.info("delete reply: "+ rno);
		
		replyRepo.deleteById(rno);
		
		WebBoard board = new WebBoard();
		board.setBno(bno);
		
		return  new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
		
	}
	
	
	
	@Transactional
	@PutMapping("/{bno}")
	public ResponseEntity<List<WebReply>> modify(@PathVariable("bno")Long bno, 
			@RequestBody WebReply reply){
	
		log.info("modify reply: "+ reply);
		
		replyRepo.findById(reply.getRno()).ifPresent(origin -> {
			
			origin.setReplyText(reply.getReplyText());
			origin.setReplyer(reply.getReplyer());
			
			replyRepo.save(origin);
		});
		
		WebBoard board = new WebBoard();
		board.setBno(bno);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
	}
	
	private List<WebReply> getListByBoard(WebBoard board)throws RuntimeException{
		
		log.info("getListByBoard...." + board);
		return replyRepo.getRepliesOfBoard(board);
		
	}
	
	
}



