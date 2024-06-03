package com.example.catstagram.controller;

import com.example.catstagram.controller.dto.CatDto;
import com.example.catstagram.controller.dto.CatResDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class CatController {
    RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public ResponseEntity<CatResDto> cat() {
        CatDto rb = restTemplate.getForObject("https://cataas.com/cat?json=true", CatDto.class);
        return ResponseEntity.ok(new CatResDto(rb.get_id()));
    }

}
