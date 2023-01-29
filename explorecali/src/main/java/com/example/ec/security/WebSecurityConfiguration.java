package com.example.ec.security;

import com.example.ec.repo.RoleRepository;

import jakarta.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ExploreCaliUserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // protected void configure(HttpSecurity http) throws Exception {

        // Entry points
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers("/users/signin").permitAll()
                .requestMatchers("/packages/**").permitAll()
                .requestMatchers("/tours/**").permitAll()
                .requestMatchers("/ratings/**").permitAll()
                // Disallow everything else..
                .anyRequest().authenticated());

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(new JwtTokenFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public Filter configure(WebSecurity web) throws Exception {
        // Allow swagger to be accessed without authentication
        web.ignoring().requestMatchers("/v2/api-docs")//
                .requestMatchers("/swagger-resources/**")//
                .requestMatchers("/swagger-ui.html")//
                .requestMatchers("/configuration/**")//
                .requestMatchers("/webjars/**")//
                .requestMatchers("/public");

        return web.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(14);
    }

}