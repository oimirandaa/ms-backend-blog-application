package dev.oscarmiranda.projects.blog_backend.repositories;

import dev.oscarmiranda.projects.blog_backend.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostsRepository  extends JpaRepository<Post, UUID> {
}
