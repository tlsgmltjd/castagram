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
@IdClass(LikesId.class)
public class Likes {
    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Cat cat;
}
