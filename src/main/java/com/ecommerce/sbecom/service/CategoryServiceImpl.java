package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.exceptions.ApiException;
import com.ecommerce.sbecom.exceptions.ResourceNotFoundException;
import com.ecommerce.sbecom.model.Category;
import com.ecommerce.sbecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if(categories.isEmpty()) {
            throw new ApiException("There are no categories");
        }
        return categories;
    }


    @Override
    public void createCategory(Category category) {
        Category savedCategory = this.categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null) {
            throw new ApiException("Category with name " + category.getCategoryName() + " already exists!!!");
        }
        this.categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long id) {

        Category deletedCategory = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", id));

        categoryRepository.delete(deletedCategory);

        return "Category with category id " + id +" deleted successfully!";
    }

    @Override
    public Category updateCategory(Category category,Long id) {
          Category savedCategory = this.categoryRepository.findById(id).
                  orElseThrow(() -> new ResourceNotFoundException("Category", "category id", id));
          savedCategory.setCategoryName(category.getCategoryName());
          this.categoryRepository.save(savedCategory);
          return savedCategory;
    }



}
