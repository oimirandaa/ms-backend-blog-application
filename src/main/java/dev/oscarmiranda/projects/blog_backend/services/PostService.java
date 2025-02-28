package dev.oscarmiranda.projects.blog_backend.services;

import dev.oscarmiranda.projects.blog_backend.domain.CreatePostRequest;
import dev.oscarmiranda.projects.blog_backend.domain.DTOs.CreatePostRequestDto;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Post;
import dev.oscarmiranda.projects.blog_backend.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post  createPost(User user, CreatePostRequest createPostRequest);
}
