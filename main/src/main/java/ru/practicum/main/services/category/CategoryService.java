package ru.practicum.main.services.category;

import ru.practicum.main.models.category.Category;

import java.util.List;

public interface CategoryService {

    /**
     * Adds a new Category and returns a Category object that was created.
     * @param: Category object - new object.
     */
    Category create(Category category);

    /**
     * Updates a Category and return the updated Category.
     * @param: Category object - updated object.
     */
    Category update(Category category);

    /**
     * Returns a list of categories by page.
     * @param from integer value of the initial object on the page.
     * @param size integer number of objects on page.
     */
    List<Category> getCategories(Integer from, Integer size);

    /**
     * Returns a category by category id.
     * @param id Category identifier.
     */
    Category getCategory(Long id);

    /**
     * Removing a Category by category id.
     * @param id Category identifier.
     */
    void delete(Long id);

}
