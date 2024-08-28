package com.example.QrOrder.configuarations;

import com.example.QrOrder.filter.JwtTokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll()
//                                .requestMatchers("qr_order/v1/user/**").permitAll()
//                                .requestMatchers("qr_order/v1/api/orders/**").permitAll()
//                                .requestMatchers("qr_order/v1/api/menu/**").permitAll()
                            //  .requestMatchers("qr_order/v1/api/menu/**").authenticated()
                );
        return http.build();
    }
}
