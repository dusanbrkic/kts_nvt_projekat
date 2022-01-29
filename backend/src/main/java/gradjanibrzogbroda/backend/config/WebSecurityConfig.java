package gradjanibrzogbroda.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import gradjanibrzogbroda.backend.security.auth.RestAuthenticationEntryPoint;
import gradjanibrzogbroda.backend.security.auth.TokenAuthenticationFilter;
import gradjanibrzogbroda.backend.service.UserDetailsServiceImplementation;
import gradjanibrzogbroda.backend.util.TokenUtils;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private UserDetailsServiceImplementation userService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userService); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

			.authorizeRequests().antMatchers("/auth/login").permitAll()
								.antMatchers("/jela/all").permitAll()
								.antMatchers("/jela/page/**").permitAll()
								.antMatchers("/jela/id/**").permitAll()
								.antMatchers("/jela/naziv/**").permitAll()
								.antMatchers(HttpMethod.GET,"/zone").permitAll()
								.antMatchers(HttpMethod.GET,"/porudzbine").permitAll()
								.antMatchers("/porudzbine/zaSankera").permitAll()
								.antMatchers("/porudzbine/zaKuvara").permitAll()
								.antMatchers("/porudzbine/status/**").permitAll()
								.antMatchers(HttpMethod.GET,"/porudzbine/{id}").permitAll()
								.antMatchers("/jelo-porudzbine/preuzeta").permitAll()
								.antMatchers("/pice/all").permitAll()
								.antMatchers("/pice/page/**").permitAll()
								.antMatchers("/pice/id/**").permitAll()
								.antMatchers("/pice/naziv/**").permitAll()
								.antMatchers("/websocket/**").permitAll()
								//.antMatchers("/**").permitAll()
			.anyRequest().authenticated().and()
			.cors().and()
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userService), BasicAuthenticationFilter.class);
		http.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		 web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}
}
