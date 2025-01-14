package dev.amrish.productservices.dtos;

import lombok.Getter;
import lombok.Setter;

// Lombok --> To reduce boilerplate code
@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;

    public String getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
