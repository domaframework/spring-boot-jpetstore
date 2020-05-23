package sample.service;

import org.springframework.stereotype.Service;
import sample.entity.Category;
import sample.repository.CategoryRepository;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category getCategory(String categoryId) {
    return categoryRepository.selectCategory(categoryId);
  }
}
