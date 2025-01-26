package dev.amrish.productservices.services;

import dev.amrish.productservices.exception.ProductNotFoundException;
import dev.amrish.productservices.models.Category;
import dev.amrish.productservices.models.Product;
import dev.amrish.productservices.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;

    SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new NullPointerException("No product Found");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = productRepository.findAllBy();
        return products;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public Product updateProduct(String title, String description, String image, String category, double price) {

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category category1 = new Category();
        category1.setTitle(title);
        product.setCategory(category1);

        productRepository.save(product);

        return product;
    }

    @Override
    public Product replaceProduct(Long id, String title, String description, String image, String category, double price) {


        return null;
    }
}
