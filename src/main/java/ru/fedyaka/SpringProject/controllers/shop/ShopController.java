package ru.fedyaka.SpringProject.controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.fedyaka.SpringProject.dto.OrderDto;
import ru.fedyaka.SpringProject.dto.ProductDto;
import ru.fedyaka.SpringProject.entity.OrderEntity;
import ru.fedyaka.SpringProject.service.ShopService;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/shop")
public class ShopController {

    ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService){
        this.shopService = shopService;
    }



    @GetMapping("/{page}/{size}")
    public Set<ProductDto> getPageGoodsForCatalog(@PathVariable int page, @PathVariable int size){
        return shopService.findPageDtoForCatalog(page, size);
    }

    @GetMapping("/{id}")
    public ProductDto getGoodsById(@PathVariable long id){
        return shopService.findById(id);
    }

    @PostMapping("/allById")
    public List<ProductDto> getAllById(@RequestBody Long[] arrayId){//@RequestBody обрабатывает json объект и помещает в следующую переменную
        return shopService.findAllById(arrayId);
    }

    @PostMapping("/order")
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        shopService.createOrder(orderDto);
        return orderDto;
    }
}
