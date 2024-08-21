package com.example.family_authorization.infrastructurer.config;

import com.example.family_authorization.domain.entity.UserProfile;
import com.example.family_authorization.domain.repository.UserProfileRepository;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mahfuzur Rahman
 * @Date 8/21/2024
 */

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class TokenConfiguration {

    private final UserProfileRepository userProfileRepository;

    @Bean
    OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator(
            OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer,
            JWKSource<SecurityContext> jwkSource) {
        NimbusJwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource);
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
        jwtGenerator.setJwtCustomizer(jwtTokenCustomizer);
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }

    @Bean
    OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        return context -> {
            if (context.getTokenType() == OAuth2TokenType.ACCESS_TOKEN && context.getAuthorizationGrantType() != AuthorizationGrantType.CLIENT_CREDENTIALS) {
                UserDetails userDetails;
                if (context.getPrincipal() instanceof UsernamePasswordAuthenticationToken) {
                    userDetails = (UserDetails) context.getPrincipal().getPrincipal();
                } else if (context.getPrincipal() instanceof OAuth2ClientAuthenticationToken) {
                    userDetails = (UserDetails) context.getPrincipal().getDetails();
                } else {
                    throw new IllegalStateException("Unexpected token type");
                }

                if (!StringUtils.hasText(userDetails.getUsername())) {
                    throw new IllegalStateException("Bad UserDetails, username is empty");
                }

                Set<String> authorities = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet());

                if (!userDetails.getUsername().equals("admin")) {
                    final var user = userProfileRepository.findByUserNameAndIsDeletedFalse(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("No User Found"));
                    context.getClaims().claim("id", user.getId());
                    context.getClaims().claim("name", user.getName());
                }
                context.getClaims().claim("authorities", authorities);
            }

        };
    }
}
