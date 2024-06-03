package com.example.catstagram.controller;

import com.example.catstagram.controller.dto.SignupDto;
import com.example.catstagram.domain.User;
import com.example.catstagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupDto dto) {

        if (userRepository.existsByName(dto.getName()))
            throw new RuntimeException("duplicated name");

        User user = User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .likes(Arrays.asList()).build();

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
