package dev.oscarmiranda.projects.blog_backend.services.impl;

import dev.oscarmiranda.projects.blog_backend.domain.CreatePostRequest;
import dev.oscarmiranda.projects.blog_backend.domain.PostStatus;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Category;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Post;
import dev.oscarmiranda.projects.blog_backend.domain.entities.Tags;
import dev.oscarmiranda.projects.blog_backend.domain.entities.User;
import dev.oscarmiranda.projects.blog_backend.repositories.PostsRepository;
import dev.oscarmiranda.projects.blog_backend.services.CategoryService;
import dev.oscarmiranda.projects.blog_backend.services.PostService;
import dev.oscarmiranda.projects.blog_backend.services.TagsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostsRepository postsRepository;
    private final TagsService tagsService;
    private final CategoryService categoryService;

    private final int WORDS_PER_MINUTE = 200;

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tags tag = tagsService.getTagById(tagId);

            return postsRepository.findAllByStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag
            );
        }

        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postsRepository.findAllByStatusAndCategory(
                    PostStatus.PUBLISHED,
                    category
            );
        }

        if (tagId != null) {
            Tags tag = tagsService.getTagById(tagId);
            return postsRepository.finaAllByStatusAndTagsContaining(
                    PostStatus.PUBLISHED,
                    tag
            );
        }

        return  postsRepository.finAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDraftPosts(User user) {
        return postsRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

    @Override
    @Transactional
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        Post newPost = new Post();

        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setStatus(createPostRequest.getStatus());
        newPost.setAuthor(user);
        newPost.setReadingTime(calculateReadingTime(createPostRequest.getContent()));

        Category category = categoryService.getCategoryById(createPostRequest.getCategoryId());
        newPost.setCategory(category);

        Set<UUID> tagIds = createPostRequest.getTagsIds();
        List<Tags> tags = tagsService.getTagsById(tagIds);
        newPost.setTags(new HashSet<>(tags));

        return postsRepository.save(newPost);
    }

    private Integer calculateReadingTime(String content){
        if (content == null || content.isEmpty()) return 0;

        int wordCount = content.trim().split("\\s+").length;

        return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
    }
}
