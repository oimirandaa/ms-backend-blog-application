package dev.oscarmiranda.projects.blog_backend.repositories;

import dev.oscarmiranda.projects.blog_backend.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostsRepository  extends JpaRepository<Post, UUID> {
}
