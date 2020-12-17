package com.aad.demo.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ClientRegistrationRepository configurer;

    public WebSecurityConfiguration(ClientRegistrationRepository configurer) {
        this.configurer = configurer;
    } 

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(a -> a
                .antMatchers("/").permitAll()
                .anyRequest().authenticated())
            .csrf(c -> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .logout(l -> l.logoutSuccessUrl("/").permitAll())        
            .oauth2Login()
            .authorizationEndpoint()
            .authorizationRequestResolver(new CustomOIDCResolver(
                configurer, "b2c"));
        ;
    }
}