package com.ipap.springboot3security.controller;

import com.ipap.springboot3security.entity.UserInfo;
import com.ipap.springboot3security.service.UserInfoUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserInfoUserDetailsService userDetailsService;

    @PostMapping("/new")
    public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userDetailsService.addUser(userInfo));
    }
}
