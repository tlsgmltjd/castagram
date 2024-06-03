package com.example.catstagram.repository;

import com.example.catstagram.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, Long> {
    Optional<Cat> findByCatId(String catId);
}
