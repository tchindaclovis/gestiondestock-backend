package com.tchindaClovis.gestiondestock.config;

import com.tchindaClovis.gestiondestock.services.auth.ApplicationUserDetailsService;
import com.tchindaClovis.gestiondestock.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class ApplicationRequestFilter extends OncePerRequestFilter { //filtre pour intercepter les requêtes

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization"); // obtenir le header avec la valeur "Authorization
        String userEmail = null;
        String jwt = null;
        String idEntreprise = null;

        if (StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7); //extraire le header
            userEmail = jwtUtil.extractUsername(jwt); //extraire le username à partir de jwt
            idEntreprise = jwtUtil.extractIdEntreprise(jwt);
        }

        if (StringUtils.hasLength(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail); //je recupère l'utilisateur en utilisant le userDetailsService
            if (jwtUtil.validateToken(jwt, userDetails)){ //vérifi si le token est valide pour cette utilisateur
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities() //chager mon utilisateur en créant un objet de type
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request) //je donne les détails à traver la requête
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken); // récupérer le contexte et établir l'authentification
            }
        }
        MDC.put("idEntreprise",idEntreprise); //pour stocker mes objets idEntreprise
        chain.doFilter(request, response);
    }
}
