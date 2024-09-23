package com.ecommerce.sbecom.controller;

import com.ecommerce.sbecom.model.Product;
import com.ecommerce.sbecom.payload.ProductDTO;
import com.ecommerce.sbecom.payload.ProductResponse;
import com.ecommerce.sbecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO, @PathVariable Long categoryId) {
        ProductDTO productDTO1 = this.productService.addProduct(productDTO,categoryId);
        return new ResponseEntity<>(productDTO1,HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts() {
        ProductResponse productResponse = this.productService.getAllProducts();
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
}
