package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Distribution;
import com.example.demo.repository.DistributionRepository;

@Service
public class DistributionService {
    @Autowired
    private DistributionRepository distributionRepository;

    public List<Distribution> getAllDistributions() {
        return distributionRepository.findAll();
    }

    public Distribution saveDistribution(Distribution distribution) {
        return distributionRepository.save(distribution);
    }

    public Optional<Distribution> getDistributionById(Long id) {
        return distributionRepository.findById(id);
    }

    public void deleteDistribution(Long id) {
        distributionRepository.deleteById(id);
    }
}
