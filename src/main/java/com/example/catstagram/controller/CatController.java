package com.example.catstagram.controller;

import com.example.catstagram.controller.dto.*;
import com.example.catstagram.domain.Cat;
import com.example.catstagram.domain.Likes;
import com.example.catstagram.domain.User;
import com.example.catstagram.repository.CatRepository;
import com.example.catstagram.repository.LikesRepository;
import com.example.catstagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CatController {
    RestTemplate restTemplate = new RestTemplate();
    private final UserRepository userRepository;
    private final CatRepository catRepository;
    private final LikesRepository likesRepository;

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<CatResDto> cat() {
        CatDto rb = restTemplate.getForObject("https://cataas.com/cat?json=true", CatDto.class);
        return ResponseEntity.ok(new CatResDto(rb.get_id()));
    }

    @PostMapping("/like/{catId}")
    @Transactional
    public ResponseEntity<Void> like(@PathVariable String catId,
                                     @RequestBody LikeReqDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(RuntimeException::new);

        Cat cat = catRepository.findByCatId(catId).orElse(null);

        if (likesRepository.existsByUserAndCat(user, cat))
            throw new RuntimeException();

        likesRepository.save(Likes.builder()
                .cat(cat != null ? cat : catRepository.save(Cat.builder()
                        .catId(catId).build()))
                .user(user).build());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/feed")
    public ResponseEntity<List<FeedDto>> feed(@RequestBody FeedReqDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(
                user.getLikes().stream()
                        .map(like -> new FeedDto(like.getCat().getId(), like.getCat().getCatId())).toList()
        );
    }

}
