package org.hwi.persistence;

import java.util.List;

import org.hwi.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	public List<Board> findBoardByTitle(String title);

	public List<Board> findByBnoGreaterThanOrderByBnoDesc(long l, Pageable paging);
}
