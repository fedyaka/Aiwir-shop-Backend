package ru.fedyaka.SpringProject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fedyaka.SpringProject.entity.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
}
