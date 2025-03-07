package dev.oscarmiranda.projects.blog_backend.services.impl;

import dev.oscarmiranda.projects.blog_backend.domain.entities.Tags;
import dev.oscarmiranda.projects.blog_backend.repositories.TagsRepository;
import dev.oscarmiranda.projects.blog_backend.services.TagsService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {
    private final TagsRepository tagsRepository;

    @Override
    public List<Tags> getTags() {
        return tagsRepository.findAllByPostCount();
    }

    @Override
    @Transactional
    public List<Tags> createTags(Set<String> tagNames) {
        List<Tags> existingTags = tagsRepository.findByNameIn(tagNames);

        Set<String>  existingTagNames = existingTags.stream()
                .map(Tags::getName)
                .collect(Collectors.toSet());

        List<Tags> newTags = tagNames
                .stream()
                .filter(name ->
                        !existingTagNames.contains(name))
                .map(name ->
                        Tags.builder().name(name)
                                .posts(new HashSet<>())
                                .build())
                .toList();

        List<Tags> savedTags = new ArrayList<>();

        if (!newTags.isEmpty()) savedTags = tagsRepository.saveAll(newTags);

        return savedTags;
    }

    @Override
    @Transactional
    public void deleteTag(UUID id) {
        tagsRepository.findById(id)
                .ifPresent(
                        tag ->
                                {
                                    if (!tag.getPosts().isEmpty()) {
                                        throw new IllegalStateException("Cannot delete tag with posts");
                                    }
                                    tagsRepository.deleteById(id);
                                }
                );
    }

    @Override
    public Tags getTagById(UUID id) {
        return tagsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No tag with id " + id + "Found!"));
    }

    @Override
    public List<Tags> getTagsById(Set<UUID> ids) {
        List<Tags> foundTags = tagsRepository.findAllById(ids);

        if (foundTags.size() != ids.size()) {
            throw new EntityNotFoundException("Not All Specified tag ids exists");
        }

        return foundTags;
    }
}
