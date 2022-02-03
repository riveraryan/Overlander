package edu.greenriver.sdev.myspringproject.config;

import edu.greenriver.sdev.myspringproject.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration file for Spring Security
 * @author Ryan Rivera
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private LoginService service;

    /**
     * Constructor
     * @param service user details service
     */
    public SecurityConfiguration(LoginService service)
    {
        this.service = service;
    }

    /**
     * Method adds a BCryptPasswordEncoder bean to the context for encrypting passwords
     * @return BCryptPasswordEncoder bean
     */
    @Bean
    public BCryptPasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }

    /*
    * Method configures User authentication
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        BCryptPasswordEncoder encoder = encoder();
        auth
                .userDetailsService(service)
                .passwordEncoder(encoder);
    }

    /*
    * Method configures Spring Security to lockdown or ignore specified url paths
    * */
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web
                .ignoring().antMatchers("/resources/**")
                .and()
                .ignoring().antMatchers("/h2-console/**");
    }

    /**
     * Configures permissions (authorization) and the login/logout
     * routines on the app.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                    .antMatchers("/overlander/adminDashboard")
                        .hasAnyAuthority("ROLE_ADMIN")
                    .antMatchers("/overlander/apiDataDisplay")
                        .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                    .antMatchers("/overlander/events/**")
                        .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .antMatchers("/**")
                        .permitAll()
                    .and()
                        .formLogin()
                            .permitAll()
                            .loginPage("/overlander/login") //Custom login page
                            .defaultSuccessUrl("/overlander/events/all")
                            .failureUrl("/overlander/login?error=true")
                    .and()
                        .logout()
                            .permitAll()
                            .logoutUrl("/overlander/logout")
                            .logoutSuccessUrl("/overlander/login?logout=true")
                    .and()
                        .csrf()
                            .disable();
    }
}