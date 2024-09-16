package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.model.Category;
import com.ecommerce.sbecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }


    @Override
    public void createCategory(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long id) {
        List<Category> categories = this.categoryRepository.findAll();
        Category category = categories.stream().filter(c -> c.getCategoryId() == id).findFirst().
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        categoryRepository.delete(category);

        return "Category with category id " + id +" deleted successfully!";
    }

    @Override
    public Category updateCategory(Category category,Long id) {
          Category savedCategory = this.categoryRepository.findById(id).
                  orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category with id " + id + " not found "));
          savedCategory.setCategoryName(category.getCategoryName());
          this.categoryRepository.save(savedCategory);
          return savedCategory;
    }



}
