package com.platzi.market.web.security.filter;

import com.platzi.market.domain.service.PlatziUserDetailsService;
import com.platzi.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader!= null && authorizationHeader.startsWith("Bearer")) {
            String jwt = authorizationHeader.substring(7); //quitando Bearer y espacios tenemos el token
            String username = jwtUtil.extractUserName(jwt);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { //no hay ya una autenticación para el usuario
                UserDetails userDetails = platziUserDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //detalles de la conexión: cuando se conectó, con qué navegador
                                //y sistema operativo, etc.
                    SecurityContextHolder.getContext().setAuthentication(authToken); //ponemos la autenticación para que no pase otra vez por todo el proceso
                            //es por lo que hemos preguntado en el segundo if si es null

                }
            }
        }

        filterChain.doFilter(request,response);

        //queda ir al SecurityConfig y decir que es este filtro el que procesa las request
    }
}
