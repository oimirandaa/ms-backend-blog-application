package dev.oscarmiranda.projects.blog_backend.domain.entities;

import dev.oscarmiranda.projects.blog_backend.domain.PostStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Column(nullable = false)
    private Integer readingTime;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(name, post.name) && Objects.equals(content, post.content) && status == post.status && Objects.equals(readingTime, post.readingTime) && Objects.equals(createdAt, post.createdAt) && Objects.equals(updatedAt, post.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, status, readingTime, createdAt, updatedAt);
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
