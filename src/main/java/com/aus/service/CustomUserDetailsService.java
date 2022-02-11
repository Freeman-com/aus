package com.aus.service;

import com.aus.model.XUser;
import com.aus.repository.UserRepositoryJPA;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class  CustomUserDetailsService implements UserDetailsService {
    private final UserRepositoryJPA userRepositoryJPA;

    public CustomUserDetailsService(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        XUser xUser = userRepositoryJPA.findByEmail(email);

        if (xUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + email);
        }

        UserDetails user;
        user = User.builder()
                .username(xUser.getEmail())
                .password(xUser.getPassword())
                .roles(xUser.getRole())
                .build();
        return user;
    }


}
