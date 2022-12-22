package com.example.TeacherAppServer.config;

import com.example.TeacherAppServer.filter.JwtBasedAuthenticationFilter;
import com.example.TeacherAppServer.filter.JwtBasedAuthorizationFilter;
import com.example.TeacherAppServer.service.UserService;
import com.example.TeacherAppServer.token.JWTTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper objectMapper;
    private final JWTTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/api/v1/authentication/*")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(getAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    private JwtBasedAuthenticationFilter getAuthenticationFilter() throws Exception {
        JwtBasedAuthenticationFilter filter = new JwtBasedAuthenticationFilter(objectMapper, authenticationManager(), jwtTokenUtil);
        filter.setFilterProcessesUrl("/api/v1/authentication/login");
        return filter;
    }

    private JwtBasedAuthorizationFilter getAuthorizationFilter() throws Exception {
        return new JwtBasedAuthorizationFilter(jwtTokenUtil, userService);
    }
}
