package dev.oscarmiranda.projects.blog_backend.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tags tags = (Tags) o;
        return Objects.equals(id, tags.id) && Objects.equals(name, tags.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
