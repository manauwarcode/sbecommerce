package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.model.Category;
import com.ecommerce.sbecom.payload.CategoryDTO;
import com.ecommerce.sbecom.payload.CategoryResponse;


import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long id);
    Category updateCategory(Category category, Long id);

}
