package dev.oscarmiranda.projects.blog_backend.controllers;

import dev.oscarmiranda.projects.blog_backend.domain.DTOs.CreateTagsRequest;
import dev.oscarmiranda.projects.blog_backend.domain.DTOs.TagDto;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Tags;
import dev.oscarmiranda.projects.blog_backend.mappers.TagsMapper;
import dev.oscarmiranda.projects.blog_backend.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagsController {
    private final TagsService tagsService;
    private final TagsMapper tagsMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<Tags> tags = tagsService.getTags();

        List<TagDto> tagRespons = tags.stream().map(tagsMapper::toTagResponse).toList();

        return ResponseEntity.ok(tagRespons);
    }

    @PostMapping
    public ResponseEntity<List<TagDto>> createTag(@RequestBody CreateTagsRequest createTagsRequest){
        List<Tags> savedTags = tagsService.createTags(createTagsRequest.getNames());

        List<TagDto> createdTagRespons = savedTags
                .stream()
                .map(tagsMapper::toTagResponse)
                .toList();

        return new ResponseEntity<>(
                createdTagRespons,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id){
        tagsService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
