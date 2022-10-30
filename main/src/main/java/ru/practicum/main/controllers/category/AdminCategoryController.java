package ru.practicum.main.controllers.category;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.services.category.CategoryService;
import ru.practicum.main.dto.category.CategoryDto;
import ru.practicum.main.dto.category.NewCategoryDto;
import ru.practicum.main.mappers.category.CategoryMapper;
import ru.practicum.main.models.category.Category;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/admin/categories")
@RequiredArgsConstructor
@Validated
public class AdminCategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @PatchMapping
    public CategoryDto update(@NotNull @Valid @RequestBody CategoryDto categoryDto) {
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
