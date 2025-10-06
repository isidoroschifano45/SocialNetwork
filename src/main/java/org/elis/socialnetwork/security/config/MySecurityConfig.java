package org.elis.socialnetwork.security.config;

import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.model.Ruolo;
import org.elis.socialnetwork.security.filter.MyAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class MySecurityConfig {
    private final MyAuthFilter myFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpConfigurer) throws Exception{
        httpConfigurer.csrf(t->t.disable());
        httpConfigurer
                .sessionManagement(t->t
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpConfigurer.authorizeHttpRequests(t -> {
            t.requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**"
            ).permitAll();
            t.requestMatchers("/login").permitAll();
            t.requestMatchers("/registrazione").permitAll();
            t.requestMatchers(HttpMethod.DELETE,"/utenti/**").hasAnyRole("ADMIN");
            t.anyRequest().authenticated();
        });

        httpConfigurer.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
        return httpConfigurer.build();
    }
}
