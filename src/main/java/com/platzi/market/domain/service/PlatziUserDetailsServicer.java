package com.platzi.market.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;

@Service
public class PlatziUserDetailsServicer implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //{noop} para indicar que la contraseña está en claro, no codificada
        //ArrayList de los permisos
        return new User( "nuria","{noop}platzi", new ArrayList<>());
    }
}
