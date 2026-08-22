package com.onlinepharmacy.catalog_prescription_service.repository;

import com.onlinepharmacy.catalog_prescription_service.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
