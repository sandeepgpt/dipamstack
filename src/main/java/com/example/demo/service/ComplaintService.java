package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Complaint;
import com.example.demo.repository.ComplaintRepository;

@Service
public class ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint saveComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    public Optional<Complaint> getComplaintById(Long id) {
        return complaintRepository.findById(id);
    }

    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }

    public void resolveComplaint(Long id) {
        Optional<Complaint> complaint = complaintRepository.findById(id);
        complaint.ifPresent(c -> {
            c.setResolved(true);
            complaintRepository.save(c);
        });
    }
}
