package br.com.udx.transportapi.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		final String QUERY_USUARIO 	= "SELECT id_usuario, email, senha, perfil FROM transport.usuario WHERE email = ? ";
		final String QUERY_PERFIL 	= "SELECT u.email, p.descricao FROM transport.usuario u INNER JOIN transport.perfil p ON u.perfil = p.id_perfil WHERE u.email = ? ";
		
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(QUERY_USUARIO)
			.authoritiesByUsernameQuery(QUERY_PERFIL)
			.passwordEncoder(this.passwordEncoder());
		
		//String encoded=new BCryptPasswordEncoder().encode("admin@123");
		//System.out.println("SENHA: "+encoded);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/admin/**")
			.hasAnyRole("COLABORADOR", "MOTORISTA")
			.and()
			.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error")
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutSuccessUrl("/login?logout")
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			.and()
			.csrf();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
