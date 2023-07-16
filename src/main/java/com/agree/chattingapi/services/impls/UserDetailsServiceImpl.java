package com.agree.chattingapi.services.impls;

import com.agree.chattingapi.dtos.user.LoginRequest;
import com.agree.chattingapi.dtos.user.UserDetailsDto;
import com.agree.chattingapi.entities.UserInfo;
import com.agree.chattingapi.services.publics.UserService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        LoginRequest request = new LoginRequest(id);

        if (id == null || id.equals("")) {
            return userService.login(request)
                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getId()))))
                    .orElseThrow(() -> new AuthenticationServiceException(id));
        }
        // 비밀번호가 맞지 않는 경우
        else {
            return userService.login(request)
                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getId()))))
                    .orElseThrow(() -> new BadCredentialsException(id));
        }
    }
}
