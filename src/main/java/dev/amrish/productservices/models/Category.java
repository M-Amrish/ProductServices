package dev.amrish.productservices.models;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Category extends BaseModel {

    private String title;

    private List<Product> products;

    public String getTitle() {
        return title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
