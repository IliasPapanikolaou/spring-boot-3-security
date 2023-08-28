package com.ipap.springboot3security.service;

import com.ipap.springboot3security.config.UserInfoUserDetails;
import com.ipap.springboot3security.entity.Role;
import com.ipap.springboot3security.entity.UserInfo;
import com.ipap.springboot3security.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Use email as username
        return userInfoRepository.findByEmail(email)
                .map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        // Create only users, not admins!
        userInfo.setRoles(List.of(Role.ROLE_USER));
        UserInfo user = userInfoRepository.save(userInfo);
        return "User " + user.getEmail() + " has been stored to database";
    }
}
