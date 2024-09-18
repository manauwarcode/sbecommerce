package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.exceptions.ApiException;
import com.ecommerce.sbecom.exceptions.ResourceNotFoundException;
import com.ecommerce.sbecom.model.Category;
import com.ecommerce.sbecom.payload.CategoryDTO;
import com.ecommerce.sbecom.payload.CategoryResponse;
import com.ecommerce.sbecom.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize){

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);

        Page<Category> categoryPage = this.categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();

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
    public CategoryDTO deleteCategory(Long id) {

        Category deletedCategory = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", id));

        CategoryDTO categoryDTO = this.modelMapper.map(deletedCategory, CategoryDTO.class);
        this.categoryRepository.delete(deletedCategory);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        Category updatingCategory = this.categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category", "category id", id));
        updatingCategory.setCategoryName(category.getCategoryName());
        Category updatedCategory = this.categoryRepository.save(updatingCategory);
        return this.modelMapper.map(updatedCategory, CategoryDTO.class);
    }



}
