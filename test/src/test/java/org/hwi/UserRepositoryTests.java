package org.hwi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.java.Log;

import org.hwi.domain.User;
import org.hwi.persistence.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setId("pizzu");
		user.setPwd("password");
		user.setLevel("1");
		log.info("이전");
		userRepo.save(user);
		log.info("이후");
		
	}
}