package dev.oscarmiranda.projects.blog_backend.mappers;

import dev.oscarmiranda.projects.blog_backend.domain.CreatePostRequest;
import dev.oscarmiranda.projects.blog_backend.domain.DTOs.CreatePostRequestDto;
import dev.oscarmiranda.projects.blog_backend.domain.DTOs.PostDto;
import dev.oscarmiranda.projects.blog_backend.domain.DTOs.UpdatePostRequestDto;
import dev.oscarmiranda.projects.blog_backend.domain.UpdatePostRequest;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);
    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto updatePostRequestDto);
    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);
}
