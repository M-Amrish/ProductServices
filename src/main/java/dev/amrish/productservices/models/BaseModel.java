package dev.amrish.productservices.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass // Shared fields or behaviour between multiple entities.
public class BaseModel {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Auto-increment primary key
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
