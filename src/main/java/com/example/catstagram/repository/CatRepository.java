package com.example.catstagram.repository;

import com.example.catstagram.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
