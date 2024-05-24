package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
import com.example.eksamensprojektbilabonnement.repositories.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaseService {
    @Autowired
    LeaseRepository leaseRepository;

    public void createLease(String chassisNumber, int customerId, LocalDate startDate, LocalDate endDate, String terms) {
        validateLeaseDates(startDate, endDate);
        leaseRepository.createLease(chassisNumber, customerId, startDate, endDate, terms);
    }
    public void validateLeaseDates(LocalDate startDate, LocalDate endDate) {
        LocalDate minDate = LocalDate.of(2024, 1, 1);
        LocalDate maxDate = LocalDate.of(2026, 12, 31);
        // her sikre vi at de datoer kontrakterne har er gyldige.
        if (startDate.isBefore(minDate) || startDate.isAfter(maxDate) ||
                endDate.isBefore(minDate) || endDate.isAfter(maxDate) ||
                endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Dates must be between 2024, and 2026, and end date must be after start date.");
        }
    }

    public List<LeaseAgreement> getLeases() {
        return leaseRepository.getLeases();

    }

    public LeaseAgreement getActiveLease(String chassisNumber) {
        return leaseRepository.getActiveLease(chassisNumber);
    }

    public void concludeLease(int leaseId) {
        leaseRepository.concludeLease(leaseId);
    }

    public LeaseAgreement getLease(int leaseId) {
        return leaseRepository.getLease(leaseId);
    }


    public boolean checkLeaseAvailability(String carChassisNumber, LocalDate startDateNew, LocalDate endDateNew) {
        // Check if the starting date is later than the end date
        if (startDateNew.isAfter(endDateNew)) {
            return false;
        }

        List<LeaseAgreement> nonConcludedLeases = getNonConcludedLeases(carChassisNumber);

        for (LeaseAgreement lease : nonConcludedLeases) {
            if (isOverlapping(lease.getStartDate(), lease.getEndDate(), startDateNew, endDateNew)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOverlapping(LocalDate existingStart, LocalDate existingEnd, LocalDate newStart, LocalDate newEnd) {
        return !(newEnd.isBefore(existingStart) || newStart.isAfter(existingEnd));
    }


    public List<LeaseAgreement> getNonConcludedLeases(String chassisNumber) {
        return leaseRepository.getNonConcludedLeases(chassisNumber);
    }
}
