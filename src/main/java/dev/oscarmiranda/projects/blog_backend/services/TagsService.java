package dev.oscarmiranda.projects.blog_backend.services;

import dev.oscarmiranda.projects.blog_backend.domain.entities.Tags;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagsService {
    List<Tags> getTags();
    List<Tags> createTags(Set<String> tagNames);
    void deleteTag(UUID id);
    Tags getTagById(UUID id);
    List<Tags> getTagsById(Set<UUID> ids);
}
