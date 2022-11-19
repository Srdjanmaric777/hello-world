package com.comtrade.helloworld.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (requests) -> {
                    try {
                        requests
                                .antMatchers("/admin").authenticated()
                                .antMatchers("/secure/hello").authenticated()
                                .antMatchers("/**").permitAll()
                                .antMatchers("/h2/**").permitAll()
                                .and().formLogin()
                                .loginPage("/login")
                                .permitAll()
                                .and().logout()
                                .logoutSuccessUrl("/hello");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                );
                /*.formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout()
                .logoutSuccessUrl("/hello");*/

        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin =
                User.withUsername("admin")
                        .password(passwordEncoder().encode("password"))
                        .authorities("ADMIN")
                        .build();

        UserDetails user1 =
                User.withUsername("user1")
                        .password(passwordEncoder().encode("password1"))
                        .authorities("USER")
                        .build();

        UserDetails user2 =
                User.withUsername("user2")
                        .password(passwordEncoder().encode("password2"))
                        .authorities("USER")
                        .build();

        return new InMemoryUserDetailsManager(admin, user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}