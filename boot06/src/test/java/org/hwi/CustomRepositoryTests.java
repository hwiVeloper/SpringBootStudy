package org.hwi;

import java.util.Arrays;

import org.hwi.persistence.CustomCrudRepository;
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
public class CustomRepositoryTests {
	@Autowired
	CustomCrudRepository repo;
	
	@Test
	public void test1() {
		Pageable pageable = PageRequest.of(0, 10, Direction.DESC, "bno");
		
		String type = "w";
		String keyword = "user09";
		
		Page<Object[]> result = repo.getCustomPage(type, keyword, pageable);
		
		log.info("" + result);
		
		log.info("TOTAL PAGES: " + result.getTotalPages());
		
		log.info("TOTAL SIZE: " + result.getTotalElements());
		
		result.getContent().forEach(arr -> {
			log.info(Arrays.toString(arr));
		});
	}
}
