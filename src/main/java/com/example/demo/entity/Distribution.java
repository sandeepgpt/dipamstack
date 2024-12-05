package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "distributions")
public class Distribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private int quantityDistributed;
    
    private LocalDate distributionDate;

	public Distribution() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Distribution(Long id, Product product, Employee employee, int quantityDistributed,
			LocalDate distributionDate) {
		super();
		this.id = id;
		this.product = product;
		this.employee = employee;
		this.quantityDistributed = quantityDistributed;
		this.distributionDate = distributionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getQuantityDistributed() {
		return quantityDistributed;
	}

	public void setQuantityDistributed(int quantityDistributed) {
		this.quantityDistributed = quantityDistributed;
	}

	public LocalDate getDistributionDate() {
		return distributionDate;
	}

	public void setDistributionDate(LocalDate distributionDate) {
		this.distributionDate = distributionDate;
	}

    
}
