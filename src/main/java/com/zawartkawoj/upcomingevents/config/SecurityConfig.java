package com.zawartkawoj.upcomingevents.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .headers(customizer -> customizer.disable())
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/home", "/").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/user").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().hasAnyAuthority("USER", "ADMIN"))
                .formLogin(login -> login
                        .loginPage("/home")
                        .loginProcessingUrl("/home")
                        .defaultSuccessUrl("/user")
                        .failureUrl("/home?error=true")
                        .permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/home"))
                .build();
    }

}


