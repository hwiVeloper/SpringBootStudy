package org.hwi;

import org.hwi.domain.Board;
import org.hwi.persistence.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testInsert() {
		Board board = new Board();
		board.setTitle("이것은 타이틀입니다.");
		board.setContent("이것은 게시물의 내용입니다.");
		board.setWriter("hwi");
		
		//boardRepo.save(board);
	}
	
	@Test
	public void testRead() {
		boardRepo.findById(1L).ifPresent((board) -> {
			System.out.println(board);
		});
	}
	
}
