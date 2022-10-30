package ru.practicum.main.mappers.category;

import org.mapstruct.*;
import ru.practicum.main.dto.category.NewCategoryDto;
import ru.practicum.main.models.category.Category;
import ru.practicum.main.dto.category.CategoryDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    Category toCategory(NewCategoryDto newCategoryDto);

    List<CategoryDto> toCategoryDtos(List<Category> categories);

}
