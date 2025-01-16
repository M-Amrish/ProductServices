package dev.amrish.productservices.services;

import dev.amrish.productservices.dtos.FakeStoreProductDto;
import dev.amrish.productservices.models.Category;
import dev.amrish.productservices.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
        // Converting from Fakestore to Product
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
    public Product deleteProduct(Long id) {

        String url = "https://fakestoreapi.com/products/"+ id;

          ResponseEntity<FakeStoreProductDto> responseEntity =  restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    null,
                    FakeStoreProductDto.class
            );
        return convertFakeStoreDtoToProduct(responseEntity.getBody());
    }


    @Override
    public Product updateProduct(String title, String description, String image, String category, double price) {

            FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

            fakeStoreProductDto.setTitle(title);
            fakeStoreProductDto.setDescription(description);
            fakeStoreProductDto.setImage(image);
            fakeStoreProductDto.setCategory(category);
            fakeStoreProductDto.setPrice(price);

           FakeStoreProductDto response =  restTemplate.postForObject(
                    "https://fakestoreapi.com/products",
                    fakeStoreProductDto,
                    FakeStoreProductDto.class
            );


        return convertFakeStoreDtoToProduct(response);
    }

    @Override
    public Product replaceProduct(Long id,String title, String description, String image, String category, double price) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);

        HttpEntity<FakeStoreProductDto> http = new HttpEntity<FakeStoreProductDto>(fakeStoreProductDto);

       ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate
               .exchange(
                       "https://fakestoreapi.com/products/"+id,
                        HttpMethod.PUT,
                       http,
                       FakeStoreProductDto.class,
                       id
               );
        return convertFakeStoreDtoToProduct(responseEntity.getBody());
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
