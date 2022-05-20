package ru.fedyaka.SpringProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fedyaka.SpringProject.dto.OrderDto;
import ru.fedyaka.SpringProject.dto.ProductDto;
import ru.fedyaka.SpringProject.entity.OrderEntity;
import ru.fedyaka.SpringProject.entity.OrderItemEntity;
import ru.fedyaka.SpringProject.entity.ProductEntity;

import ru.fedyaka.SpringProject.repository.CategoryRepository;
import ru.fedyaka.SpringProject.repository.OrderItemRepository;
import ru.fedyaka.SpringProject.repository.OrderRepository;
import ru.fedyaka.SpringProject.repository.paging.ProductPagingRepository;
import ru.fedyaka.SpringProject.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ShopService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductPagingRepository productPagingRepository;
    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;


    @Autowired
    public ShopService(ProductRepository productRepository,
                       CategoryRepository categoryRepository,
                       ProductPagingRepository productPagingRepository,
                       OrderRepository orderRepository,
                       OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productPagingRepository = productPagingRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }


    public Set<ProductDto> findPageDtoForCatalog(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> products = productPagingRepository.findAll(pageable);

        Set<ProductDto> dtoSet = new HashSet<>();

        for (ProductEntity product : products){
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setCost(product.getCost());
            dto.setImage(product.getImage());
            dtoSet.add(dto);
        }
        return dtoSet;
    }

    public ProductDto findById(long id){
        ProductEntity entity = productRepository.findById(id).get();
        ProductDto dto = new ProductDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCost(entity.getCost());
        dto.setCategory(entity.getCategory().getName());
        dto.setImage(entity.getImage());

        return dto;
    }

    public List<ProductDto> findAllById(Long[] arrayId){
        List<ProductDto> dtoSet = new ArrayList<>();
        for(ProductEntity entity : productRepository.findAllById(Arrays.asList(arrayId))){
            ProductDto dto = new ProductDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCost(entity.getCost());
            dto.setImage(entity.getImage());
            dtoSet.add(dto);
        }

        return dtoSet;
    }

    public void createOrder(OrderDto orderDto){
        OrderEntity order = new OrderEntity();

        order.setName(orderDto.getName());
        order.setSurname(orderDto.getSurname());
        order.setPhoneNumber(orderDto.getPhoneNumber());
        order.setAddress(orderDto.getAddress());
        order.setDescription(orderDto.getDescription());
        order.setStartDate(LocalDateTime.now());

        List<OrderItemEntity> items = new ArrayList<>();
        order.setOrderItems(items);
        orderRepository.save(order);
        for (Map.Entry<Long, Integer> entry : orderDto.getCart().entrySet()){
            ProductEntity product = productRepository.findById(entry.getKey()).get();

            OrderItemEntity item = new OrderItemEntity();
            item.setOrder(order);
            item.setProduct(product);
            item.setAmount(entry.getValue());

            items.add(item);

            orderItemRepository.save(item);
        }
        order.setOrderItems(items);

        orderRepository.save(order);
    }

}
