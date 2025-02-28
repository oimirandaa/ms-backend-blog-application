package dev.oscarmiranda.projects.blog_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostRequest {
    private UUID id;

    private String title;

    private String content;

    private UUID categoryId;

    @Builder.Default
    private Set<UUID> tagsIds = new HashSet<>();

    private PostStatus status;
}
