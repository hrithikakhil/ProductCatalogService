package org.example.productcatalogservice.controller;

import org.example.productcatalogservice.dtos.CategoryDto;
import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;


    @GetMapping
    public List<ProductDto> getProducts(){
        List<Product> result = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : result) {
            productDtos.add(getProductDto(product));
        }

        return productDtos;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId){
        Product product = productService.getProductById(productId);
        try{
            if (productId<=0){
                throw new IllegalArgumentException("Product is Invalid");
            }
            ProductDto productDto = getProductDto(product);

            //Customizing the header details
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("called by", "Hrithik M");

            return new ResponseEntity<>(productDto, headers, HttpStatus.OK);
        }catch (IllegalArgumentException exception){
            throw exception;
        }

    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){

        Product product = getProduct(productDto);
        Product result = productService.createProduct(product);
        return getProductDto(result);
    }

    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        Product product = getProduct(productDto);
        Product result = productService.replaceProduct(product, id);
        return getProductDto(result);
    }

    private ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(productDto.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImageurl(product.getImageurl());

        if (product.getCategory()!=null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            productDto.setCategoryDto(categoryDto);
        }

        return productDto;
    }

    private Product getProduct(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageurl(productDto.getImageurl());

        if (productDto.getCategoryDto()!=null){
            Category category = new Category();
            category.setId(productDto.getCategoryDto().getId());
            category.setName(productDto.getCategoryDto().getName());
            product.setCategory(category);
        }

        return product;
    }
}
