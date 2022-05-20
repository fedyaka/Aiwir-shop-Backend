package ru.fedyaka.SpringProject.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fedyaka.SpringProject.entity.OrderEntity;
import ru.fedyaka.SpringProject.repository.paging.OrderPagingRepository;

@Service
public class OrderService {

    OrderPagingRepository orderPaging;

    @Autowired
    public OrderService(OrderPagingRepository orderPaging){
        this.orderPaging = orderPaging;
    }

    public Page<OrderEntity> getPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderEntity> pageOrders = orderPaging.findAll(pageable);
        return pageOrders;
    }
}
