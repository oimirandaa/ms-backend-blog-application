package dev.oscarmiranda.projects.blog_backend.services;

import dev.oscarmiranda.projects.blog_backend.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
