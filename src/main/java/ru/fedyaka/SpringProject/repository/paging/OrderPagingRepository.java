package ru.fedyaka.SpringProject.repository.paging;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.fedyaka.SpringProject.entity.OrderEntity;

public interface OrderPagingRepository extends PagingAndSortingRepository<OrderEntity, Long> {
}
