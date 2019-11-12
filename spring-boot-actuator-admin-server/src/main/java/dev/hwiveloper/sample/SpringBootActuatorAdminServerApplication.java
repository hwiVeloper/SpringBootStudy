package dev.hwiveloper.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class SpringBootActuatorAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActuatorAdminServerApplication.class, args);
	}

}
