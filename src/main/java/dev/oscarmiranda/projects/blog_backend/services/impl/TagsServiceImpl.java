package dev.oscarmiranda.projects.blog_backend.services.impl;

import dev.oscarmiranda.projects.blog_backend.domain.entities.Tags;
import dev.oscarmiranda.projects.blog_backend.repositories.TagsRepository;
import dev.oscarmiranda.projects.blog_backend.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {
    private final TagsRepository tagsRepository;

    @Override
    public List<Tags> getTags() {
        return tagsRepository.findAllByPostCount();
    }
}
