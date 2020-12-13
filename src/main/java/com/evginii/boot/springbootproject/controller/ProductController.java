package com.evginii.boot.springbootproject.controller;

import com.evginii.boot.springbootproject.model.Product;
import com.evginii.boot.springbootproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String showAll(Model model,
                          @RequestParam(required = false, name = "min_cost") BigDecimal minCost,
                          @RequestParam(required = false, name = "max_cost") BigDecimal maxCost) {
        model.addAttribute("products", productService.getAllProducts(minCost,maxCost));
        return "all-products";
    }

    @GetMapping("/remove/{id}")
    public String removeProductByID(@PathVariable Long id) {
        productService.removeById(id);
        return "redirect:/products";
    }

    @PostMapping("/add")
    public String saveOrUpdate(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }
}
