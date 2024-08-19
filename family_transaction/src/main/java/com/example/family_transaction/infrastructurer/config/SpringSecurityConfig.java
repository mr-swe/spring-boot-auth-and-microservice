package com.example.family_transaction.infrastructurer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Mahfuzur Rahman
 * @Date 8/17/2024
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().authenticated() // Require authentication for all other requests
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection using the new lambda-based configuration
                .formLogin(formLogin -> formLogin.loginPage("/login")) // Specify custom login page if needed
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()) // Configure JWT authentication converter
                        )
                );
        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(
                jwt -> {
                    List<String> userRoleAuthorities = jwt.getClaimAsStringList("authorities");

                    if (userRoleAuthorities == null) {
                        userRoleAuthorities = Collections.emptyList();
                    }

                    JwtGrantedAuthoritiesConverter scopesConverter = new JwtGrantedAuthoritiesConverter();

                    Collection<GrantedAuthority> scopeAuthorities = scopesConverter.convert(jwt);

                    scopeAuthorities
                            .addAll(userRoleAuthorities.stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .toList());

                    return scopeAuthorities;
                }
        );

        return converter;
    }
}
