package org.hwi;

import java.util.stream.IntStream;

import org.hwi.domain.WebBoard;
import org.hwi.persistence.WebBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebBoardRepositoryTests {
	@Autowired
	WebBoardRepository repo;
	
	//@Test
	public void insertBoardDummies() {
		IntStream.range(0, 300).forEach(i -> {
			WebBoard board = new WebBoard();
			
			board.setTitle("Sample Board Title " + i);
			board.setContent("Content Sample ... " + i + "of Board");
			board.setWriter("user0" + (i % 10));
			
			repo.save(board);
		});
	}
	
	public void testList1() {
		Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "bno");
		
		Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), pageable);
		
		log.info("PAGE: " + result.getPageable());
		
		log.info("-----------------------------");
		result.getContent().forEach(board -> log.info("" + board));
	}
	
	@Test
	public void testList2() {
		Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "bno");
		
		Page<WebBoard> result = repo.findAll(repo.makePredicate("t", "10"), pageable);
		
		log.info("PAGE: " + result.getPageable());
		
		log.info("-----------------------------");
		result.getContent().forEach(board -> log.info("" + board));
	}
}
