package ru.fedyaka.SpringProject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.fedyaka.SpringProject.entity.OrderItemEntity;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Long> {
}
