package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewInventory(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "inventory_list"; // Make sure there's a Thymeleaf template named `inventory_list.html`
    }
}
