package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories = new ArrayList<>();

    private Long uniqueId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }


    @Override
    public void createCategory(Category category) {
        category.setCategoryId(uniqueId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long id) {
        Category category = categories.stream().filter(c -> c.getCategoryId() == id).findFirst().
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));

        categories.remove(category);

        return "Category with category id " + id +" deleted successfully!";
    }
}
