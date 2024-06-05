package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.ChangeLog;
import com.example.eksamensprojektbilabonnement.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public List<ChangeLog> getChangeLogs() {
        return adminRepository.getChangeLogs();
    }
}
