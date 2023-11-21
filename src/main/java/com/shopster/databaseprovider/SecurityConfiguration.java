package com.shopster.databaseprovider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity httpSec) throws Exception {
        return httpSec.authorizeHttpRequests(auth -> {
            auth.anyRequest().authenticated();
        }).httpBasic(http -> {}).csrf(AbstractHttpConfigurer::disable).build(); 
    }
}
