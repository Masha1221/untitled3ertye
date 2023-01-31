package org.example.configs;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {


    private final UserAuthenticationProvider authProvider;

    public ConfigSecurity(UserAuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/files/upload/**")
                .authenticated()
                .and()
                .httpBasic();
        http.authenticationProvider(authProvider);

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}


