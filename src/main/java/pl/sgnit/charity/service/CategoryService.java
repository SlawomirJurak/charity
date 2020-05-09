package pl.sgnit.charity.service;

import org.springframework.stereotype.Service;
import pl.sgnit.charity.model.Category;
import pl.sgnit.charity.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
