package dev.amrish.productservices.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity // Mapped to a database table
public class Category extends BaseModel {

    private String title;

    // often deal with relationships like @OneToMany or @ManyToOne.
    // These can cause infinite recursion during JSON serialization.
    @JsonIgnore// to avoid infinite loops.
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
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
