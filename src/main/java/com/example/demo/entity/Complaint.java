package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String complaintDetails;
    private LocalDate dateOfComplaint;

    private boolean resolved = false;  // Indicates whether the complaint has been resolved

	public Complaint() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Complaint(Long id, Employee employee, Product product, String complaintDetails, LocalDate dateOfComplaint,
			boolean resolved) {
		super();
		this.id = id;
		this.employee = employee;
		this.product = product;
		this.complaintDetails = complaintDetails;
		this.dateOfComplaint = dateOfComplaint;
		this.resolved = resolved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getComplaintDetails() {
		return complaintDetails;
	}

	public void setComplaintDetails(String complaintDetails) {
		this.complaintDetails = complaintDetails;
	}

	public LocalDate getDateOfComplaint() {
		return dateOfComplaint;
	}

	public void setDateOfComplaint(LocalDate dateOfComplaint) {
		this.dateOfComplaint = dateOfComplaint;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

    
}
