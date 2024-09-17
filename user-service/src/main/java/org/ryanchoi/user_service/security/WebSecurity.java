package org.ryanchoi.user_service.security;

import org.ryanchoi.user_service.service.UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class WebSecurity {

    private static final String MY_IP = "74.102.125.219";

    private static final String[] WHITE_LIST_GET = {
            "/actuator/**",
            "/h2-console/**",
            "/welcome",
            "/health-check",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**"
    };

    private static final String[] WHITE_LIST_POST = {
            "/users"
    };

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;

    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, Environment env) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.env = env;
    }

//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
////        AuthenticationManagerBuilder authenticationManagerBuilder =
////                http.getSharedObject(AuthenticationManagerBuilder.class);
////        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
////        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
//
//        http.csrf( (csrf) -> csrf.disable() );
//
//        http.authorizeHttpRequests(
//                authorize -> authorize
//                        .requestMatchers(WHITE_LIST_GET).permitAll()
//                        .requestMatchers(HttpMethod.POST, WHITE_LIST_POST).permitAll()
//                        .requestMatchers("/**").access(
//                                new WebExpressionAuthorizationManager("hasIpAddress('localhost') or hasIpAddress('127.0.0.1') or hasIpAddress('" + MY_IP + "')"))
//                        .anyRequest().authenticated()
//        );
//
//        http.headers((headers) -> headers.frameOptions( (frameOptions) -> frameOptions.sameOrigin()));
//
//        return http.build();
//    }

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                  .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(WHITE_LIST_GET).permitAll()
                                .requestMatchers( PathRequest.toH2Console() ).permitAll()
                                .anyRequest().authenticated()
                )
                .build();
    }

    private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        return new AuthenticationFilter(authenticationManager, userService, env);
    }
}
