package dev.amrish.productservices.repositories;

import dev.amrish.productservices.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
