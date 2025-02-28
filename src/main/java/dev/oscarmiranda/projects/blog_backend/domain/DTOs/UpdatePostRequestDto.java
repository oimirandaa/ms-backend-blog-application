package dev.oscarmiranda.projects.blog_backend.domain.DTOs;

import dev.oscarmiranda.projects.blog_backend.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePostRequestDto {
    @NotNull(message = "Post Id is required")
    private UUID id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title name must be between {min} and {max} character")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10, max = 50_000, message = "Content name must be between {min} and {max} character")
    private String content;

    @NotNull(message = "Category id cannot be null")
    private UUID categoryId;

    @Builder.Default
    @Size(max = 10, message = "Maximum {max} tags allowed")
    private Set<UUID> tagsIds = new HashSet<>();

    @NotNull(message = "Status is required")
    private PostStatus status;
}
