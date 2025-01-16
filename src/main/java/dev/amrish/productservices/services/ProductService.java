package dev.amrish.productservices.services;

import dev.amrish.productservices.models.Product;

import java.util.List;


public interface ProductService {

    Product getSingleProduct(Long id);

    List<Product> getAllProduct();

    Product deleteProduct(Long id);

    Product updateProduct(String title, String description, String image, String category, double price);


    Product replaceProduct(Long id, String title, String description, String image, String category, double price);
}
