package dev.amrish.productservices.controllers;

import dev.amrish.productservices.dtos.CreateProductDto;
import dev.amrish.productservices.dtos.ErrorDto;
import dev.amrish.productservices.exception.ProductNotFoundException;
import dev.amrish.productservices.models.Product;
import dev.amrish.productservices.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(@Qualifier("selfProductService") ProductService productService){
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

    // POST - Create Product
    @PostMapping
    public Product createProduct(@RequestBody CreateProductDto createProductDto){
        return  productService.updateProduct(
                createProductDto.getTitle(),
                createProductDto.getDescription(),
                createProductDto.getImage(),
                createProductDto.getCategory(),
                createProductDto.getPrice()
        );
    }

    // Put -  Replace the entire Product object.
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody CreateProductDto createProductDto){

        return productService.replaceProduct(
                id,
                createProductDto.getTitle(),
                createProductDto.getDescription(),
                createProductDto.getImage(),
                createProductDto.getCategory(),
                createProductDto.getPrice()

        );
    }


    // DELETE - Delete Product
    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id){

        return productService.deleteProduct(id);
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorDto> handleNullPointerException(){
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage("Something went wrong. Please try again");
//
//        return new ResponseEntity<>(errorDto,HttpStatusCode.valueOf(404));
//    }


}
