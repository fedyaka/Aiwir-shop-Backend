package ru.fedyaka.SpringProject.controllers.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fedyaka.SpringProject.service.admin.CategoryService;
import ru.fedyaka.SpringProject.service.admin.ProductService;

import java.util.Arrays;
import java.util.Set;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    ProductService productService;
    CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String products(Model model){
        model.addAttribute("products", productService.findAll());
        return "product/products";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "product/add";
    }

    @PostMapping("/add")
    public String create(@RequestParam(name = "name") String name,
                         @RequestParam(name = "description") String description,
                         @RequestParam(name = "cost") Double  cost,
                         @RequestParam(name = "category") Long categoryId){
        productService.create(name, description, cost, categoryId);
        return "redirect:/admin/product/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") long id, Model model){
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "product/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@PathVariable(name = "id") long id,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "description") String description,
                         @RequestParam(name = "cost") Double cost,
                         @RequestParam(name = "category") Long categoryId){
        productService.update(id, name, description, cost, categoryId);
        return "redirect:/admin/product/";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") long id){
        productService.delete(id);
        return "redirect:/admin/product/";
    }
}
