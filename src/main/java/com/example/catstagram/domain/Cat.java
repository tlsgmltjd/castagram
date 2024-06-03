package com.example.catstagram.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Cat")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String catId;

    @OneToMany(mappedBy = "cat")
    private List<Likes> likes;
}
