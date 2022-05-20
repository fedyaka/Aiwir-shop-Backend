package ru.fedyaka.SpringProject.repository.paging;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.fedyaka.SpringProject.entity.ProductEntity;

public interface ProductPagingRepository extends PagingAndSortingRepository<ProductEntity, Long> {
}
