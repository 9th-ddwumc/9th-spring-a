package com.example.umc9th_chapter4.global.security;

import com.example.umc9th_chapter4.domain.user.entity.Users;
import com.example.umc9th_chapter4.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        if (user.getRole() == null) {
            return Collections.emptyList();
        }
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()));
    }
}
