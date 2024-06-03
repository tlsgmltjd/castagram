package com.example.catstagram.repository;

import com.example.catstagram.domain.Cat;
import com.example.catstagram.domain.Likes;
import com.example.catstagram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    boolean existsByUserAndCat(User user, Cat cat);
}
