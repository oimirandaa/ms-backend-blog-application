package dev.oscarmiranda.projects.blog_backend.repositories;

import dev.oscarmiranda.projects.blog_backend.domain.entities.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TagsRepository extends JpaRepository<Tags, UUID> {

    @Query("SELECT t FROM Tags t LEFT JOIN FETCH t.posts")
    List<Tags> findAllByPostCount();
    List<Tags> findByNameIn(Set<String> tagNames);
}
