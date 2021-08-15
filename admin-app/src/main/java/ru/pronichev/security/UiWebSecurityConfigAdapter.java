package ru.pronichev.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class UiWebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/**/*.css", "/**/*.js").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/**").authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_processing")
            .defaultSuccessUrl("/user")
            .and()
            .exceptionHandling()
            .accessDeniedPage("/access_denied");
    }
}