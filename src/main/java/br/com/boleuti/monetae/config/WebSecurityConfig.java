package br.com.boleuti.monetae.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import br.com.boleuti.monetae.model.Permissao;
import br.com.boleuti.monetae.model.TipoLancamento;
import br.com.boleuti.monetae.repositories.PermissaoRepository;
import br.com.boleuti.monetae.repositories.TipoLancamentoRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Autowired
    private PermissaoRepository roleRepository;
	
	@Autowired
    private TipoLancamentoRepository tipoLancamentoRepository;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	Permissao roleAdmin = new Permissao();
    	roleAdmin.setNome("ADMIN");
    	roleRepository.save(roleAdmin);
    	
    	TipoLancamento recebimento = new TipoLancamento();
    	recebimento.setNome("RECEBIMENTO");
    	tipoLancamentoRepository.save(recebimento);
    	TipoLancamento pagamento = new TipoLancamento();
    	pagamento.setNome("PAGAMENTO");
    	tipoLancamentoRepository.save(pagamento);
    	
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