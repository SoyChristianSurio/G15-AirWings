package com.airwings.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.authorizeRequests().antMatchers("/**","/css/**","/js/**","/img/**").permitAll()
		.antMatchers("/gestion/paises").hasAnyAuthority("gestion_paises")					//hasAnyAuthority para cualquier string
		.antMatchers("/gestion/").hasAnyRole("ROLE_admin","ROLE_admin_aeropuerto")									//hasAnyRole, el string debe iniciar con "ROLE_"
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
	}
	
	
}
