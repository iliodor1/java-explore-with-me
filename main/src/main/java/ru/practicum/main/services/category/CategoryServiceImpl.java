
package ru.practicum.main.services.category;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.main.models.category.Category;
import ru.practicum.main.repositories.category.CategoryRepository;
import ru.practicum.main.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public Category create(@NonNull Category category) {
        return repository.save(category);
    }

    @Override
    public Category update(@NonNull Category category) {
        getCategory(category.getId());

        return repository.save(category);
    }

    @Override
    public List<Category> getCategories(Integer from, Integer size) {
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable).toList();
    }

    @Override
    public Category getCategory(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error("The category '{}' was not found.", id);
            throw new NotFoundException(String.format("The category id '%d' was not found.", id));
        });
    }

    @Override
    public void delete(Long id) {
        Optional<Category> category = repository.findById(id);

        if (category.isPresent()) {
            repository.deleteById(id);
        } else {
            log.error("The category '{}' was not found.", id);
            throw new NotFoundException(String.format("The category id '%d' was not found.", id));
        }
    }

}
