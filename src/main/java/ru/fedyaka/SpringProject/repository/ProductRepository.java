package ru.fedyaka.SpringProject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fedyaka.SpringProject.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
