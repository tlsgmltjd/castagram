package com.example.catstagram.controller;

import com.example.catstagram.controller.dto.LoginResDto;
import com.example.catstagram.controller.dto.SignupDto;
import com.example.catstagram.domain.User;
import com.example.catstagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    @Transactional
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

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<LoginResDto> login(@RequestBody SignupDto dto) {
        User user = userRepository.findByName(dto.getName())
                .orElseThrow(() -> new RuntimeException("not found user"));

        if (!user.validatePassword(dto.getPassword()))
            throw new RuntimeException("not matched password");

        return ResponseEntity.ok(new LoginResDto(user.getId()));
    }
}
