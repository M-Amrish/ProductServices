package dev.amrish.productservices.controllers;

import dev.amrish.productservices.models.Product;
import dev.amrish.productservices.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }
    // Jackson --> to convert Java objects to JSON (serialization)
    // and JSON back to Java objects (deserialization).
    // get single product
    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id){
       return productService.getSingleProduct(id);
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    public void createProduct(){

    }

    public void deleteProduct(){

    }



}
