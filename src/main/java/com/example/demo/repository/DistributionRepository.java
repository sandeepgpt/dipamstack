package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Distribution;

public interface DistributionRepository extends JpaRepository<Distribution, Long> {
}
