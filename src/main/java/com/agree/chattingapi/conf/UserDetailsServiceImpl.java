package com.agree.chattingapi.conf;

import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<UserInfo> userOptional = userRepository.findById(id);
        UserInfo user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found: " + id));

        return new org.springframework.security.core.userdetails.User(
                user.getId(),
                user.getPw(),
                getAuthorities(Collections.singleton(user.getRoles()))
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
