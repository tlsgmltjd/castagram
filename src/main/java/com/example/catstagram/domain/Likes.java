package com.example.catstagram.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Likes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Likes {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;
}
