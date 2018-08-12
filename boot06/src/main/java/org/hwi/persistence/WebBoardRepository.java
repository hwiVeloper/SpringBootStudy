package org.hwi.persistence;

import org.hwi.domain.QWebBoard;
import org.hwi.domain.WebBoard;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard>{
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		
		QWebBoard board = QWebBoard.webBoard;
		
		// type if ~ else
		
		// bno > 0
		builder.and(board.bno.gt(0));
		
		if (type == null) {
			return builder;
		}
		
		switch(type) {
		case "t":
			builder.and(board.title.like("%" + keyword + "%"));
			break;
		case "c":
			builder.and(board.content.like("%" + keyword + "%"));
		case "w":
			builder.and(board.writer.like("%" + keyword + "%"));
		}
		
		return builder;
	}
}
