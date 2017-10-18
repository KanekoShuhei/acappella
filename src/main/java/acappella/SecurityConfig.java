package acappella;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	// 以下に記載したパスはセキュリティ対象外
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// ログアウト状態でもリクエストを許可するパスを指定
		http.authorizeRequests()
				.antMatchers("/", "/user_register/**", "/loginform/**", "/video/list/**","/video/detail/**",
						"/video/get-all-json/**","/video/get-band-videos-json/**","/video/get-one-json-by-youtubeId/**","/video/get-one-json-by-login-user/**",
						"/band/list/**","/band/detail/**","/band/ranking/**","/band/get-one-json/**","/band/get-all-json/**","/video/get-one-like-count/**",
						"/band/get-love-bands-by-login-user/**")
				.permitAll()
				.antMatchers("/item-list","/item-save").hasRole("ADMIN")				
				.anyRequest().authenticated();
		http.formLogin().loginProcessingUrl("/login").loginPage("/loginform").failureUrl("/loginform?error=true")
				.defaultSuccessUrl("/video/list", true).usernameParameter("email").passwordParameter("password").and();
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/video/list")
				.deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll();
		http.exceptionHandling().accessDeniedPage("/video/list");
	}

	@Configuration
	static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		@Autowired
		UserDetailsService userDetailsService;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(new StandardPasswordEncoder());
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}
}
