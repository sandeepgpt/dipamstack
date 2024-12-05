package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}

