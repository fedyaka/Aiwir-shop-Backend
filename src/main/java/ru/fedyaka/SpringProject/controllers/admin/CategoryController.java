package ru.fedyaka.SpringProject.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fedyaka.SpringProject.entity.CategoryEntity;
import ru.fedyaka.SpringProject.service.admin.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String categories(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "category/categories";
    }

    @GetMapping("/add")
    public String add(){
        return "category/add";
    }

    @PostMapping("/add")
    public String create(@RequestParam String name){
        categoryService.save(new CategoryEntity(name));
        return "redirect:/admin/category/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryService.findById(id));
        return "category/edit";
    }

    @PatchMapping("/edit/{id}")
    public String Update(@PathVariable("id") long id, @RequestParam String name){
        categoryService.update(id, name);
        return "redirect:/admin/category/";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        categoryService.delete(id);
        return "redirect:/admin/category/";
    }
}
