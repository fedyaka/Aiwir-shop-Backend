package ru.fedyaka.SpringProject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fedyaka.SpringProject.entity.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
