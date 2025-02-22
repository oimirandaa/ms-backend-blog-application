package dev.oscarmiranda.projects.blog_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import  dev.oscarmiranda.projects.blog_backend.domain.entities.User;


public interface UserRepository extends JpaRepository<User, UUID>{
}
