package com.ecommerce.sbecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotBlank
    @Size(min = 3, max = 50,message = "Product name must have at least 3 characters")
    private String productName;

    @NotBlank
    @Size(min = 6, max = 50,message = "Product name must have at least 6 characters")
    private String description;

    private String image;
    private Integer quantity;
    private Double price;
    private Double discount;
    private Double specialPrice;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
