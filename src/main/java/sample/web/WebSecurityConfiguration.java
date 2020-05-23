package sample.web;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import sample.service.AccountService;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final DataSource dataSource;
  private final AccountService accountService;

  public WebSecurityConfiguration(DataSource dataSource, AccountService accountService) {
    this.dataSource = dataSource;
    this.accountService = accountService;
  }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    var tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
    return tokenRepository;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(
            "/",
            "/cart/**",
            "/category/**",
            "/product/**",
            "/item/**",
            "/search/**",
            "/account/add",
            "/images/**",
            "/css/**",
            "/js/**",
            "/webjars/**")
        .permitAll()
        .anyRequest()
        .authenticated();
    http.formLogin()
        .loginPage("/signin")
        .permitAll()
        .and()
        .logout()
        .logoutUrl("/signout")
        .permitAll();
    http.rememberMe().tokenRepository(persistentTokenRepository());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
  }
}
