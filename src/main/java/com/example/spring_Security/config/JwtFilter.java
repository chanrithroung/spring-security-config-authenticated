package com.example.spring_Security.config;

import com.example.spring_Security.service.TokenService.JWTServices;
import com.example.spring_Security.service.userServices.MyUserDetailsServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JWTServices jwtServices;
    private final ApplicationContext context;

    @Autowired
    public JwtFilter(JWTServices jwtServices, ApplicationContext context) {
        this.jwtServices = jwtServices;
        this.context = context;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

//        token = authHeader.substring(7);
//        System.out.println( "token  = " + token);
//        username = jwtServices.extractUserName(token);
//        System.out.println("username " + username);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtServices.extractUsername(token);
        }

//        System.out.println(token);
//        System.out.println("Context holder : "+SecurityContextHolder.getContext().getAuthentication());
//        System.out.println("Username : "+ username);


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println(username);
            UserDetails userDetails = context.getBean(MyUserDetailsServices.class).loadUserByUsername(username);

            if(jwtServices.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }
        filterChain.doFilter(request, response);
    }
}
