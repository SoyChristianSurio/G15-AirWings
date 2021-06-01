package com.airwings.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.airwings.app.services.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JpaUserDetailsService userDetailService;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.authorizeRequests().antMatchers("/","/registro","/css/**","/js/**","/img/**","/webfonts/**").permitAll()
		.antMatchers("/gestion/paises").hasAnyAuthority("gestion_paises")					//hasAnyAuthority para cualquier string
		.antMatchers("/gestion").hasAnyRole("ROLE_admin","ROLE_admin_aeropuerto")			//hasAnyRole, el string debe iniciar con "ROLE_"
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(userDetailService).passwordEncoder(passEncoder);
	}
}
