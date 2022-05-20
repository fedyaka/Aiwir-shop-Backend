package ru.fedyaka.SpringProject.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping()
    public String index(){
        return "admin";
    }
}
