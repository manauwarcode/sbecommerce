package com.ecommerce.sbecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Entity(name="categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotBlank(message = "Category Name Must Not Be Blank!!")
    @Size(min =5, message = "Category Name Must Contain At Least 5 Characters")
    private String categoryName;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products;

}
