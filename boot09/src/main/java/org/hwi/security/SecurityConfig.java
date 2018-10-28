package org.hwi.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Autowired
	HwiUsersService hwiUsersService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config..............");
		
		http.authorizeRequests()
		.antMatchers("/boards/list").permitAll()
		.antMatchers("/boards/register")
		.hasAnyRole("BASIC", "MANAGER", "ADMIN");
		
		http.formLogin().loginPage("/login");
		
//		http.authorizeRequests().antMatchers("/guest/**").permitAll();
//		
//		http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
//		
//		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		
		// 세션 무효화
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
//		http.userDetailsService(hwiUsersService);
		
		http.rememberMe().key("hwi").userDetailsService(hwiUsersService)
		.tokenRepository(getJDBCRepository())
		.tokenValiditySeconds(60*60*24);
	}

	private PersistentTokenRepository getJDBCRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		log.info("build Auth global.........");

		auth.userDetailsService(hwiUsersService).passwordEncoder(passwordEncoder());
	}
}
