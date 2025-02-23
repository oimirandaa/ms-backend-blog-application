package dev.oscarmiranda.projects.blog_backend.services;

import dev.oscarmiranda.projects.blog_backend.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();
}
