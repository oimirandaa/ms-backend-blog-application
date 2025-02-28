package dev.oscarmiranda.projects.blog_backend.repositories;

import dev.oscarmiranda.projects.blog_backend.domain.PostStatus;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Category;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Post;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Tags;
import dev.oscarmiranda.projects.blog_backend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostsRepository  extends JpaRepository<Post, UUID> {
    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tags tags);
    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);
    List<Post> finaAllByStatusAndTagsContaining(PostStatus status, Tags tags);
    List<Post> finAllByStatus(PostStatus status);
    List<Post> findAllByAuthorAndStatus(User user,PostStatus status);
}
