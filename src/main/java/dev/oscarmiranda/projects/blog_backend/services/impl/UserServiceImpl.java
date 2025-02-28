package dev.oscarmiranda.projects.blog_backend.services.impl;

import dev.oscarmiranda.projects.blog_backend.domain.entities.User;
import dev.oscarmiranda.projects.blog_backend.repositories.UserRepository;
import dev.oscarmiranda.projects.blog_backend.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }
}
