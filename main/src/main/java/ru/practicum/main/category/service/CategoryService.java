package ru.practicum.main.category.service;

import ru.practicum.main.category.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);

    Category update(Category category);

    List<Category> getCategories(Integer from, Integer size);

    Category getCategory(Long id);

    void delete(Long id);

}
