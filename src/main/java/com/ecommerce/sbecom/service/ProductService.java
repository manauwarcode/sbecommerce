package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.payload.ProductDTO;
import com.ecommerce.sbecom.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;


public interface ProductService {


    ProductDTO addProduct(ProductDTO productDTO, Long categoryId);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize,String sortBy, String sortOrder);

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductsByKeyword(String keyword);

    ProductDTO updateProduct(Long productId,ProductDTO productDTO);

    ProductDTO deleteCategory(Long productId);

    ProductDTO updateProductImage(Long productId, MultipartFile image);
}
