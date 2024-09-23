package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.payload.ProductDTO;
import com.ecommerce.sbecom.repositories.ProductRepository;

public interface ProductService {


    ProductDTO addProduct(ProductDTO productDTO, Long categoryId);
}
