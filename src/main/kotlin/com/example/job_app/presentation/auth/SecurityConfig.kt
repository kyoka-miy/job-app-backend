package com.example.job_app.presentation.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.LogoutHandler

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter,
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint
) {
    @Bean
    fun configureHttpSecurity(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { r ->
                r
                    .requestMatchers("/", "/auth/register", "/auth/login").permitAll()
                    .requestMatchers("/actuator/health").permitAll()
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                // Exception handling for authentication failure
                it.authenticationEntryPoint(authenticationEntryPoint)
            }
            .csrf { it.disable() }
        return http.build()
    }
}
