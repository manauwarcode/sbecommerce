package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.exceptions.ApiException;
import com.ecommerce.sbecom.exceptions.ResourceNotFoundException;
import com.ecommerce.sbecom.model.Category;
import com.ecommerce.sbecom.payload.CategoryDTO;
import com.ecommerce.sbecom.payload.CategoryResponse;
import com.ecommerce.sbecom.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if(categories.isEmpty()) {
            throw new ApiException("There are no categories");
        }

        List<CategoryDTO> categoryDTOS = categories.stream().
                map(obj -> modelMapper.map(obj, CategoryDTO.class)).collect(Collectors.toList());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;

    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category alreadyExistingCategory = this.categoryRepository.findByCategoryName(category.getCategoryName());
        if(alreadyExistingCategory != null) {
            throw new ApiException("Category with name " + category.getCategoryName() + " already exists!!!");
        }
        Category savedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(savedCategory, CategoryDTO.class);
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
