package dev.amrish.productservices.services;

import dev.amrish.productservices.dtos.FakeStoreProductDto;
import dev.amrish.productservices.models.Category;
import dev.amrish.productservices.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) {

        String url = "https://fakestoreapi.com/products/"+id;
        // getting single product from FakeStore
        FakeStoreProductDto fakeStoreProductDto =
               restTemplate.getForObject(
                       url,
                       FakeStoreProductDto.class
               );

        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProduct() {

        FakeStoreProductDto[] fakeStoreProductDto =restTemplate.
                getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
                );

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDtos : fakeStoreProductDto){
            products.add(convertFakeStoreDtoToProduct(fakeStoreProductDtos));
        }
        return products;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public void updateProduct(Long id, Product product) {

    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();

        Category category = new Category();

        product.setCategory(category);
        product.getCategory().setTitle(fakeStoreProductDto.getCategory());

        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());

        return product;
    }
}
