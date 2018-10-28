package org.hwi.persistence;

import org.hwi.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String>{

}
