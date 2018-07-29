package org.hwi.persistence;

import java.util.List;

import org.hwi.domain.FreeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {
	public List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);
	
	@Query("SELECT b.bno, b.title, count(r) " +
	" FROM FreeBoard b LEFT OUTER JOIN b.replies r " + 
	" WHERE b.bno > 0 GROUP BY b ")
	public List<Object[]> getPage(Pageable page);
}
