package ru.fedyaka.SpringProject.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/show/{id}")
    public String show(@PathVariable long id, Model model){
        model.addAttribute("order", orderService.findById(id));
        model.addAttribute("totalSum", orderService.totalSumById(id));
        return "order/show";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable int id){
        orderService.deleteById(id);
        return "redirect:/admin/order/0/100";
    }
}
