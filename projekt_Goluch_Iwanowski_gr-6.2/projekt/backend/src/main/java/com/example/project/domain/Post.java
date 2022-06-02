package com.example.project.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Lob
    @Column(name="description", length=512)
    @NotNull
    private String description;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="creator_id")
    private User creator;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();



}
