package dev.amrish.productservices.services;

import dev.amrish.productservices.models.Product;

import java.util.List;


public interface ProductService {

    Product getSingleProduct(Long id);

    List<Product> getAllProduct();

    void deleteProduct(Long id);

    void updateProduct(Long id, Product product);
}
