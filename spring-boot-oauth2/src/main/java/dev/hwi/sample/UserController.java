package dev.hwi.sample;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}
