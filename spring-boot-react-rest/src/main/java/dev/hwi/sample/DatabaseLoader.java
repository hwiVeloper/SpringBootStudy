package dev.hwi.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
	
	private final EmployeeRepository repo;
	
	@Autowired
	public DatabaseLoader(EmployeeRepository repo) {
		this.repo = repo;
	}

	@Override
	public void run(String... args) throws Exception {
		this.repo.save(new Employee("veloper", "hwi", "devloper"));
	}

}
