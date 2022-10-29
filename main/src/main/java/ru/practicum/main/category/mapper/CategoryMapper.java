package ru.practicum.main.category.mapper;

import org.mapstruct.*;
import ru.practicum.main.category.dto.NewCategoryDto;
import ru.practicum.main.category.model.Category;
import ru.practicum.main.category.dto.CategoryDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    Category toCategory(NewCategoryDto newCategoryDto);

    List<CategoryDto> toCategoryDtos(List<Category> categories);

}
