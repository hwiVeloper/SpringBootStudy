package org.hwi.persistence;

import org.hwi.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>{

}
