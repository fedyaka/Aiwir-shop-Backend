package ru.fedyaka.SpringProject.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fedyaka.SpringProject.entity.OrderEntity;
import ru.fedyaka.SpringProject.entity.OrderItemEntity;
import ru.fedyaka.SpringProject.repository.OrderRepository;
import ru.fedyaka.SpringProject.repository.paging.OrderPagingRepository;

@Service
public class OrderService {

    OrderPagingRepository orderPaging;
    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderPagingRepository orderPaging, OrderRepository orderRepository ){
        this.orderPaging = orderPaging;
        this.orderRepository = orderRepository;
    }

    public OrderEntity findById(long id){
        return orderRepository.findById(id).get();
    }

    public Double totalSumById(long id){
        Double totalSum = 0.0;
        OrderEntity order = orderRepository.findById(id).get();
        for (OrderItemEntity orderItem : order.getOrderItems()){
            totalSum += orderItem.getProduct().getCost() * orderItem.getAmount();
        }
        return totalSum;
    }
    public Page<OrderEntity> getPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderEntity> pageOrders = orderPaging.findAll(pageable);
        return pageOrders;
    }

    public void deleteById(long id){
        orderRepository.deleteById(id);
    }
}
