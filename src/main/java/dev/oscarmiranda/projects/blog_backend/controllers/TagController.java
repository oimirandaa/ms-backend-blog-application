package dev.oscarmiranda.projects.blog_backend.controllers;

import dev.oscarmiranda.projects.blog_backend.domain.DTOs.TagResponse;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Tags;
import dev.oscarmiranda.projects.blog_backend.mappers.TagsMapper;
import dev.oscarmiranda.projects.blog_backend.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagsService tagsService;
    private final TagsMapper tagsMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tags> tags = tagsService.getTags();

        List<TagResponse> tagResponses = tags.stream().map(tagsMapper::toTagResponse).toList();

        return ResponseEntity.ok(tagResponses);
    }
}
