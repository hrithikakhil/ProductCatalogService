package org.example.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice.models.Category;

@Getter
@Setter
public class ProductDto {

    private long id;

    private String name;

    private Double price;

    private String description;

    private String imageurl;

    private CategoryDto categoryDto;

}
