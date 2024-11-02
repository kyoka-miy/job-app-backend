package com.example.job_app.presentation.auth

import com.example.job_app.usecase.jwt.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // passes the request and response to the next filter in the chain
            filterChain.doFilter(request, response)
            return
        }
        val jwt = authHeader.substring(7)
        val accountEmail = jwtService.extractUsername(jwt)
        // if the current user is not authenticated
        if (SecurityContextHolder.getContext().authentication == null) {
            val accountDetails: UserDetails = userDetailsService.loadUserByUsername(accountEmail)
            if (jwtService.isTokenValid(accountEmail, accountDetails, jwt)) {
                val authToken = UsernamePasswordAuthenticationToken(
                    accountDetails,
                    null, // user is already authenticated
                    accountDetails.authorities // roles or permissions granted to the user
                )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }
}
