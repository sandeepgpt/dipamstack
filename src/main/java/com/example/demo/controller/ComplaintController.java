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

import com.example.demo.entity.Complaint;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/complaints")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private ProductService productService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String viewComplaints(Model model) {
        model.addAttribute("complaints", complaintService.getAllComplaints());
        return "complaint_list";
    }

    @GetMapping("/add")
    public String addComplaintForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "add_complaint";
    }

    @PostMapping("/save")
    public String saveComplaint(@ModelAttribute("complaint") Complaint complaint) {
        complaint.setDateOfComplaint(LocalDate.now());
        complaintService.saveComplaint(complaint);
        return "redirect:/complaints";
    }

    @GetMapping("/edit/{id}")
    public String editComplaintForm(@PathVariable("id") Long id, Model model) {
        Optional<Complaint> complaint = complaintService.getComplaintById(id);
        if (complaint.isPresent()) {
            model.addAttribute("complaint", complaint.get());
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "edit_complaint";
        } else {
            return "redirect:/complaints";
        }
    }

    @PostMapping("/update/{id}")
    public String updateComplaint(@PathVariable("id") Long id, @ModelAttribute("complaint") Complaint updatedComplaint) {
        Optional<Complaint> existingComplaint = complaintService.getComplaintById(id);
        if (existingComplaint.isPresent()) {
            Complaint complaint = existingComplaint.get();
            complaint.setProduct(updatedComplaint.getProduct());
            complaint.setEmployee(updatedComplaint.getEmployee());
            complaint.setComplaintDetails(updatedComplaint.getComplaintDetails());
            complaint.setResolved(updatedComplaint.isResolved());
            complaintService.saveComplaint(complaint);
        }
        return "redirect:/complaints";
    }

    @GetMapping("/delete/{id}")
    public String deleteComplaint(@PathVariable("id") Long id) {
        complaintService.deleteComplaint(id);
        return "redirect:/complaints";
    }

    @GetMapping("/resolve/{id}")
    public String resolveComplaint(@PathVariable("id") Long id) {
        complaintService.resolveComplaint(id);
        return "redirect:/complaints";
    }
}

