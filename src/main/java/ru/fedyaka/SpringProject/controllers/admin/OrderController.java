package ru.fedyaka.SpringProject.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fedyaka.SpringProject.service.admin.OrderService;

@Controller
@RequestMapping("/admin/order")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/{page}/{size}")
    public String orders(@PathVariable int page, @PathVariable int size, Model model){
        model.addAttribute("orders", orderService.getPage(page, size));
        return "order/orders";
    }
}
