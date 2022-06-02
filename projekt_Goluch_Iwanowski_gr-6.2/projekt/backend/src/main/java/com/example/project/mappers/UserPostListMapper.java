package com.example.project.mappers;

import com.example.project.domain.User;
import com.example.project.model.PostListDTO;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true),
        uses = UserMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserPostListMapper {

    public abstract PostListDTO mapUserToPostListDTO(User user);
}
