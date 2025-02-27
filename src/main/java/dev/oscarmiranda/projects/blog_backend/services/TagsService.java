package dev.oscarmiranda.projects.blog_backend.services;

import dev.oscarmiranda.projects.blog_backend.domain.entities.Tags;

import java.util.List;

public interface TagsService {
    List<Tags> getTags();
}
