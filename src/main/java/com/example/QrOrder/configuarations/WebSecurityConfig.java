package com.example.QrOrder.configuarations;

import com.example.QrOrder.filter.JwtTokenFilter;
import com.example.QrOrder.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers(
                                    String.format("qr_order/v1/user/**"),
                                    String.format("qr_order/v1/api/orders/**")
                                    //  String.format("qr_order/v1/api/menu/**")
                            )
                            .permitAll()
                            .requestMatchers(GET, String.format("qr_order/v1/api/menu/**")).permitAll()
                            .requestMatchers(POST, String.format("qr_order/v1/api/menu/**")).hasRole(Role.ADMIN)
                            .requestMatchers(PUT, String.format("qr_order/v1/api/menu/**")).hasRole(Role.ADMIN)
                            .requestMatchers(DELETE, String.format("qr_order/v1/api/menu/**")).hasRole(Role.ADMIN)
                            .anyRequest().authenticated();
                });
        return http.build();
    }

}

