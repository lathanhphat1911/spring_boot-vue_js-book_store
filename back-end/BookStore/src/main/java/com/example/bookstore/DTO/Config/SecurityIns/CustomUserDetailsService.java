package com.example.bookstore.DTO.Config.SecurityIns;

import com.example.bookstore.DTO.Config.Exception.CustomResoucesNotFound;
import com.example.bookstore.Entity.User;
import com.example.bookstore.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    private String encode(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        encoder.encode(password);
        return encoder.encode(password);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws CustomResoucesNotFound {
        User user = userRepository.findUserByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new CustomResoucesNotFound(usernameOrEmail));
        System.out.println(user.getUsername());
        System.out.println(user.getRole());
        Set<SimpleGrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return new org.springframework.security.core.userdetails.User(usernameOrEmail, encode(user.getPassword()), authorities);
    }
}
