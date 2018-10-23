package com.thuannd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.thuannd.entity.User;
import com.thuannd.service.impl.AuditorAwareImpl;
import com.thuannd.utils.RoleEnum;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class ShipWebApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService detailsService;

	public static void main(String[] args) {
		SpringApplication.run(ShipWebApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasAnyRole(RoleEnum.ADMIN.getRoleName()).antMatchers("/")
				.permitAll().antMatchers("/dang-ky").permitAll().antMatchers("/shop/**")
				.hasAnyRole(RoleEnum.ADMIN.getRoleName(), RoleEnum.SHOP.getRoleName()).antMatchers("/shipper/**")
				.hasAnyRole(RoleEnum.ADMIN.getRoleName(), RoleEnum.SHIPPER.getRoleName()).antMatchers("/user/**")
				.authenticated().anyRequest().authenticated().and().formLogin().loginPage("/dang-nhap")
				.usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/", true)
				.failureUrl("/dang-nhap?e").permitAll().and().logout().logoutUrl("/dang-xuat").permitAll().and()
				.exceptionHandling().accessDeniedPage("/dang-nhap?e");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/client/js/**", "/client/css/**", "/client/fonts/**", "/client/images/**",
				"/user/css/**", "/user/js/**", "/user/fonts/**", "/user/images/**", "/user/plugins/**");
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	// custom jpa auditing
	@Bean
	public AuditorAware<User> auditorProvider() {
		return new AuditorAwareImpl();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).passwordEncoder(encoder());
	}
}
