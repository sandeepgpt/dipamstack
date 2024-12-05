package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entity.Distribution;
import com.example.demo.service.DistributionService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/distributions")
public class DistributionController {
    @Autowired
    private DistributionService distributionService;
    @Autowired
    private ProductService productService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String viewDistributions(Model model) {
        model.addAttribute("distributions", distributionService.getAllDistributions());
        return "distribution_list";
    }

    @GetMapping("/add")
    public String addDistributionForm(Model model) {
        model.addAttribute("distribution", new Distribution());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "add_distribution";
    }

    @PostMapping("/save")
    public String saveDistribution(@ModelAttribute("distribution") Distribution distribution, Model model) {
        try {
            // Check if product and employee are properly set
            if (distribution.getProduct() == null || distribution.getEmployee() == null) {
                model.addAttribute("error", "Please select both Product and Employee.");
                return "add_distribution";
            }

            // Set current date for distribution if not set
            if (distribution.getDistributionDate() == null) {
                distribution.setDistributionDate(LocalDate.now());
            }

            distributionService.saveDistribution(distribution);
            return "redirect:/distributions";
        } catch (Exception e) {
            // Log and show error details
            e.printStackTrace();
            model.addAttribute("error", "An error occurred: " + e.getMessage());
            return "add_distribution";
        }
    }

    // Method to display the update form
    @GetMapping("/edit/{id}")
    public String editDistributionForm(@PathVariable("id") Long id, Model model) {
        Optional<Distribution> distribution = distributionService.getDistributionById(id);
        if (distribution.isPresent()) {
            model.addAttribute("distribution", distribution.get());
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "edit_distribution";
        } else {
            return "redirect:/distributions";
        }
    }

    // Method to handle distribution update
    @PostMapping("/update/{id}")
    public String updateDistribution(@PathVariable("id") Long id, @ModelAttribute("distribution") Distribution updatedDistribution) {
        Optional<Distribution> existingDistribution = distributionService.getDistributionById(id);
        if (existingDistribution.isPresent()) {
            Distribution distribution = existingDistribution.get();
            distribution.setProduct(updatedDistribution.getProduct());
            distribution.setEmployee(updatedDistribution.getEmployee());
            distribution.setQuantityDistributed(updatedDistribution.getQuantityDistributed());
            
            // Update the distribution date if provided
            if (updatedDistribution.getDistributionDate() != null) {
                distribution.setDistributionDate(updatedDistribution.getDistributionDate());
            }

            distributionService.saveDistribution(distribution);
        }
        return "redirect:/distributions";
    }

    // Method to delete a distribution
    @GetMapping("/delete/{id}")
    public String deleteDistribution(@PathVariable("id") Long id) {
        distributionService.deleteDistribution(id);
        return "redirect:/distributions";
    }
}












