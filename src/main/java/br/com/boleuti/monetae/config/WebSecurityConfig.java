package br.com.boleuti.monetae.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.boleuti.monetae.model.Role;
import br.com.boleuti.monetae.repositories.RoleRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Autowired
    private RoleRepository roleRepository;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	Role roleAdmin = new Role();
    	roleAdmin.setRole("ADMIN");
    	roleRepository.save(roleAdmin);
        http
            .authorizeRequests()
            	.antMatchers("/user/**","/register", "/app/**", "/js/**", "/css/**", "/images/**", "/fonts/**", "/sass/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")                
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
			.usersByUsernameQuery("select login,senha, active enabled from User where login=?")		
			.authoritiesByUsernameQuery("select u.login, r.role from user u inner join user_role ur on(u.id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.login=?")
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
    	
//      auth
//      .inMemoryAuthentication()
//          .withUser("user").password("password").roles("USER");

    }
}