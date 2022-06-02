package com.example.project.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String text;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="post_id")
    private Post post;

}
