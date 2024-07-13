package com.vntechno.vntechno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Vô hiệu hóa CSRF cho các API không trạng thái
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/v1/public/auth/login", "/api/v1/public/auth/register").permitAll() // Cho phép truy cập GET vào các endpoint bắt đầu bằng /api/public/ mà không cần xác thực
            .anyRequest().authenticated()); // Bảo vệ tất cả các yêu cầu khác

        // .addFilterBefore(new JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    
}
