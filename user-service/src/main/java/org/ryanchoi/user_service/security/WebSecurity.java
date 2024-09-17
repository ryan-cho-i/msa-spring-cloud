package org.ryanchoi.user_service.security;

import org.springframework.core.env.Environment;
import org.ryanchoi.user_service.service.UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private static final String MY_IP = "74.102.125.219";

    private static final String[] WHITE_LIST_GET = {
            "/actuator/**",
            "/h2-console/**",
            "/welcome",
            "/health-check",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    private static final String[] WHITE_LIST_POST = {
            "/users"
    };

    private Environment env;
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(Environment env, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.env = env;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {

        http.csrf( (csrf) -> csrf.disable() );

        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers(WHITE_LIST_GET).permitAll()
                        .requestMatchers(HttpMethod.POST, WHITE_LIST_POST).permitAll()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
        );

        http.headers((headers) -> headers.frameOptions( (frameOptions) -> frameOptions.sameOrigin()));

        return http.build();
    }

    private AuthenticationFilter getAuthenticationFilter( AuthenticationManager authenticationManager ) throws Exception {
        return new AuthenticationFilter(authenticationManager, userService, env);
    }
}
