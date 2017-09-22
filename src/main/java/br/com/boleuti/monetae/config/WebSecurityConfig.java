package br.com.boleuti.monetae.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http
            .authorizeRequests()
            	.antMatchers("/user/**","/register", "/app/**", "/js/**", "/css/**", "/images/**", "/fonts/**", "/sass/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")         
                .failureUrl("/loginError")   
                .permitAll()
                .and()                
            .logout()
                .permitAll();
        http.csrf().disable();                                
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.
		jdbcAuthentication()
			.usersByUsernameQuery("SELECT LOGIN,SENHA, ACTIVE as ENABLED FROM USER WHERE LOGIN=?")		
			.authoritiesByUsernameQuery("SELECT U.LOGIN, P.NOME FROM USER U INNER JOIN USER_PERMISSAO UP ON(U.ID=UP.USER_ID) INNER JOIN PERMISSAO P ON(UP.PERMISSAO_ID=P.ID) WHERE U.LOGIN=?")
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
    	

    }
}