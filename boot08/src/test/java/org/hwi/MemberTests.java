package org.hwi;

import java.util.Arrays;
import java.util.Optional;

import org.hwi.domain.Member;
import org.hwi.domain.MemberRole;
import org.hwi.persistence.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTests {
	@Autowired
	private MemberRepository repo;
	
	//@Test
	public void testInsert() {
		for (int i = 0; i <= 100; i++) {
			Member member = new Member();
			member.setUid("user" + i);
			member.setUpw("pw" + i);
			member.setUname("사용자" + i);
			
			MemberRole role = new MemberRole();
			if (i <= 80) {
				role.setRoleName("BASIC");
			} else
			if (i <= 90) {
				role.setRoleName("MANAGER");
			} else {
				role.setRoleName("ADMIN");
			}
			member.setRoles(Arrays.asList(role));
			
			repo.save(member);
		}
	}
	
	@Test
	public void testRead() {
		Optional<Member> result = repo.findById("user90");
		
		result.ifPresent(member -> log.info("member" + member));
	}
}
