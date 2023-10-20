package io.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.audiences}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeRequests()
                .mvcMatchers("/case-record").authenticated()
                .mvcMatchers("/case-record").hasAnyAuthority("SCOPE_read:syphilis", "SCOPE_write:syphilis", "SCOPE_write:scd", "SCOPE_write:scd")
                .mvcMatchers("/questions").authenticated()
                .mvcMatchers("/questions").hasAnyAuthority("SCOPE_read:syphilis", "SCOPE_write:syphilis", "SCOPE_write:scd", "SCOPE_write:scd")
                .mvcMatchers("/search-cases").authenticated()
                .mvcMatchers("/search-cases").hasAnyAuthority("SCOPE_read:syphilis", "SCOPE_write:syphilis", "SCOPE_write:scd", "SCOPE_write:scd")
                .mvcMatchers("/*").permitAll()
                .and().cors()
                .and().oauth2ResourceServer().jwt();
        return http.build();
    }
}

