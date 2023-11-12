/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.mrsebastian.todoappdemo.frontend.configuration;

import java.net.URI;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

@Configuration
@Profile("!no-security")
public class SecurityConfiguration {

    private static final String LOGOUT_URL = "/logout";

    private static final String LOGOUT_SUCCESS_URL = "/loggedout.html";

    /**
     * Same lifetime as SSO Session (e.g. 10 hours).
     */
    @Value("${spring.session.timeout:36000}")
    private long springSessionTimeoutSeconds;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        final CookieServerCsrfTokenRepository tokenRepository = CookieServerCsrfTokenRepository.withHttpOnlyFalse();
        // requestHandler needed for handling the raw CSRF tokens
        final ServerCsrfTokenRequestAttributeHandler requestHandler = new ServerCsrfTokenRequestAttributeHandler();

        http
                .logout(
                        logoutSpec -> {
                            logoutSpec.logoutSuccessHandler(createLogoutSuccessHandler(LOGOUT_SUCCESS_URL));
                            logoutSpec.logoutUrl(LOGOUT_URL);
                            logoutSpec.requiresLogout(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, LOGOUT_URL));
                        })
                .authorizeExchange(
                        authorizeExchangeSpec -> {
                            authorizeExchangeSpec.pathMatchers(HttpMethod.OPTIONS, "/api/**").permitAll();
                            authorizeExchangeSpec.pathMatchers(LOGOUT_SUCCESS_URL).permitAll();
                            authorizeExchangeSpec.pathMatchers("/api/*/info",
                                    "/actuator/health",
                                    "/actuator/info",
                                    "/actuator/metrics").permitAll();
                            authorizeExchangeSpec.anyExchange().authenticated();
                        })
                .oauth2Login(oAuth2LoginSpec -> oAuth2LoginSpec.authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler() {
                    @Override
                    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
                        webFilterExchange.getExchange().getSession().subscribe(
                                webSession -> webSession.setMaxIdleTime(Duration.ofSeconds(springSessionTimeoutSeconds)));
                        return super.onAuthenticationSuccess(webFilterExchange, authentication);
                    }
                }))
                .csrf(csrfSpec -> {
                    csrfSpec.csrfTokenRepository(tokenRepository);
                    csrfSpec.csrfTokenRequestHandler(requestHandler);
                });

        return http.build();
    }

    /**
     * This method creates the {@link ServerLogoutSuccessHandler} for handling a successful logout.
     * The usage is necessary in {@link SecurityWebFilterChain}.
     *
     * @param uri to forward after an successful logout.
     * @return The handler for forwarding after an succesful logout.
     */
    public static ServerLogoutSuccessHandler createLogoutSuccessHandler(final String uri) {
        final RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
        successHandler.setLogoutSuccessUrl(URI.create(uri));
        return successHandler;
    }

}
