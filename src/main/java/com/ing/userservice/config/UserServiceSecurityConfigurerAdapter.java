package com.ing.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class UserServiceSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder)
        throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
            .withUser("girish").password("{noop}g1r15h").authorities("ADMIN")
            .and().withUser("guest").password("{noop}guest").authorities("USER");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.PUT, "/api/users/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("ADMIN", "USER")
            //.anyRequest().authenticated()
            .and()
            .httpBasic();
    }
}
