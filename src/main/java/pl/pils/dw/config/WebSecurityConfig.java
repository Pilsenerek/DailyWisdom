package pl.pils.dw.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private StandardPasswordEncoder StandardPasswordEncoder;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(
                		"/",
                		"/user/register",
                		"/dw/**",
                		"/images/**",
                		"/css/**",
                		"/js/**",
                		"/webjars/**"
                	).permitAll()
                
                .anyRequest().authenticated()
                
             //default path /login
             .and().formLogin()
             	//overwrite default login page
             	.loginPage("/login").successForwardUrl("/").permitAll()
             
             //default path /logout
             .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID")
             .invalidateHttpSession(true)
             .permitAll()
        ;
    }
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    	auth
      		.jdbcAuthentication()
      		.dataSource(dataSource)
      		.usersByUsernameQuery("select email,pass,1 from user where email=?")
      		.authoritiesByUsernameQuery("select email,role from user where email=?")
      		.passwordEncoder(this.StandardPasswordEncoder)
      	;
    } 
    
    @Bean
    public StandardPasswordEncoder standardPasswordEncoder() {
    	
		return new StandardPasswordEncoder();
    }

/*    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("pass").roles("USER");
    }*/
}
