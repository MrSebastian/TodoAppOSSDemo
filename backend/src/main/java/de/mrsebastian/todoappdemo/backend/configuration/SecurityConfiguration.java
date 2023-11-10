/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.mrsebastian.todoappdemo.backend.configuration;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

/**
 * The central class for configuration of all security aspects.
 */
@Configuration
@Profile("!no-security")
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Import(RestTemplateAutoConfiguration.class)
public class SecurityConfiguration {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${security.oauth2.resource.user-info-uri}")
    private String userInfoUri;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        val mvcRequestMatcher = new MvcRequestMatcher.Builder(introspector);

        http
                .authorizeHttpRequests((requests) -> requests.requestMatchers(antMatcher("/**"),
                        // allow access to /actuator/info
                        antMatcher("/actuator/info"),
                        // allow access to /actuator/health for OpenShift Health Check
                        antMatcher("/actuator/health"),
                        // allow access to /actuator/health/liveness for OpenShift Liveness Check
                        antMatcher("/actuator/health/liveness"),
                        // allow access to /actuator/health/readiness for OpenShift Readiness Check
                        antMatcher("/actuator/health/readiness"),
                        // allow access to /actuator/metrics for Prometheus monitoring in OpenShift
                        antMatcher("/actuator/metrics"))
                        .permitAll())
                .authorizeHttpRequests(requests -> requests.requestMatchers(mvcRequestMatcher.pattern("/**"))
                        .authenticated()
                        .requestMatchers(PathRequest.toH2Console()).permitAll())
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
                        .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(new JwtUserInfoAuthenticationConverter(
                                new UserInfoAuthoritiesService(userInfoUri, restTemplateBuilder)))));

        return http.build();
    }

}
