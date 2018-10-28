package org.hwi.persistence;

import org.springframework.data.repository.CrudRepository;
import org.hwi.domain.QWebBoard;
import org.hwi.domain.WebBoard;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface CustomCrudRepository 
    extends CrudRepository<WebBoard, Long>, CustomWebBoard {

	
}
