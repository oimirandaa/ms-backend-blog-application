package dev.oscarmiranda.projects.blog_backend.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagResponse {
    private UUID id;
    private String name;
    private Integer postCount;
}
