package com.lokytech.FocusBooker.security;

import com.lokytech.FocusBooker.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfiguration {

    @Autowired
    private JwtService jwtService;

    @Autowired
    @Lazy
    private UsersService usersService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                                authorizeRequests
                                        // Open registration to all Public endpoints
//                                .requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll()
                                        .requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
                                        .requestMatchers(new AntPathRequestMatcher("/register/**")).permitAll()

                                        // User Endpoints
                                        .requestMatchers(new AntPathRequestMatcher("/users")).hasRole("ADMIN")
                                        .requestMatchers(new AntPathRequestMatcher("/users/**")).hasAnyRole("ADMIN", "USER")
                                        .anyRequest().authenticated()
                )

                .addFilterBefore(new JwtAuthenticationFilter(authenticationManagerBean(), jwtService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(Arrays.asList(
                new DaoAuthenticationProvider() {{
                    setUserDetailsService(usersService);  // <-- Use your UsersService
                    setPasswordEncoder(new BCryptPasswordEncoder());
                }}
        ));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
