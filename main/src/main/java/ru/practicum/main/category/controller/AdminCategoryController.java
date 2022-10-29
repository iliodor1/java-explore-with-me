package ru.practicum.main.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.category.service.CategoryService;
import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.category.dto.NewCategoryDto;
import ru.practicum.main.category.mapper.CategoryMapper;
import ru.practicum.main.category.model.Category;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin/categories")
@RequiredArgsConstructor
@Validated
public class AdminCategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @PatchMapping
    public CategoryDto update(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = mapper.toCategory(categoryDto);

        return mapper.toDto(service.update(category));
    }

    @PostMapping
    public CategoryDto create(@Valid @RequestBody NewCategoryDto categoryDto) {
        Category category = mapper.toCategory(categoryDto);

        return mapper.toDto(service.create(category));
    }

    @DeleteMapping("{catId}")
    public void delete(@PathVariable(name = "catId") Long id) {
        service.delete(id);
    }

}
