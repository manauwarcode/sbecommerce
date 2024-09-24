package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.payload.ProductDTO;
import com.ecommerce.sbecom.payload.ProductResponse;


public interface ProductService {


    ProductDTO addProduct(ProductDTO productDTO, Long categoryId);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize,String sortBy, String sortOrder);
}
