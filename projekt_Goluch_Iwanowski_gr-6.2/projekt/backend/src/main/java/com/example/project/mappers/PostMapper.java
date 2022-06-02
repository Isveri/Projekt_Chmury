package com.example.project.mappers;

import com.example.project.domain.Post;
import com.example.project.model.PostDTO;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true),
        uses = UserMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PostMapper {

    public abstract PostDTO mapPostToPostDTO(Post post);

    public abstract Post mapPostDTOToPost(PostDTO postDTO);

}
