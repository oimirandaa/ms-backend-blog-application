package dev.oscarmiranda.projects.blog_backend.mappers;

import dev.oscarmiranda.projects.blog_backend.domain.DTOs.TagResponse;
import dev.oscarmiranda.projects.blog_backend.domain.PostStatus;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Post;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Tags;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagsMapper {
    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tags tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {
        if (posts == null) return 0;

        return (int) posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
