package com.platzi.market.web.security;

import com.platzi.market.domain.service.PlatziUserDetailsService;
import com.platzi.market.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(platziUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  //deshabilitar las peticiones cruzadas
            .authorizeRequests()
            .antMatchers("/**/authenticate").permitAll() //permite todas las peticiones que terminen en authenticate
            .anyRequest().authenticated()  //y el resto de peticiones autenticar
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //después de poner el filtro en JWTFilterRequest
                                 // doFilterInternal. Decir que Sin sesión (stateless) porque los JWT son los que van a controlar cada petición,
                                 // sin manejar una sesión como tal.

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class); //ponemos el filtro
    }

    //como hemos creado una clase AuthenticationManager, tenemos que sobrescribir este método
    @Override
    @Bean       //para elegirlo como gestor de autenticación de la api
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); //para que sea spring quien siga controlando la autenticación
    }
}
