package org.elis.socialnetwork.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elis.socialnetwork.security.config.JwtUtilities;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class MyAuthFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver resolver;
    private final JwtUtilities jwtUtilities;

    public MyAuthFilter(UserDetailsService userDetailsService,
                        @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver,
                        JwtUtilities jwtUtilities) {
        this.userDetailsService = userDetailsService;
        this.resolver = resolver;
        this.jwtUtilities=jwtUtilities;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("doFilterInternal");
        try{
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            boolean giaAutenticato = securityContext.getAuthentication()!=null;
            if(authHeader==null||giaAutenticato||!authHeader.startsWith("Bearer "))
            {
                System.out.println("GiaAutenticato");
                filterChain.doFilter(request,response);
                return;
            }
            String token = authHeader.substring(7);
            String username = jwtUtilities.getUsernameFromToken(token);

            System.out.println(username + " controllo");

            UserDetails utente = userDetailsService.loadUserByUsername(username);

            var upat = new UsernamePasswordAuthenticationToken(utente, null, utente.getAuthorities());
            upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            securityContext.setAuthentication(upat);
            filterChain.doFilter(request,response);
            return;

        }
        catch (Exception e){
            resolver.resolveException(request,response,null,e);
            filterChain.doFilter(request,response);
            return;
        }
    }
}
