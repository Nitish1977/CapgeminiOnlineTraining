package com.onlinepharmacy.catalog_prescription_service.service;

import com.onlinepharmacy.catalog_prescription_service.entity.Medicine;

import java.util.List;

public interface MedicineService {
    Medicine addMedicine(Medicine medicine);
    List<Medicine> getAllMedicine();
    Medicine getMedicineById(Long id);
    Medicine updateMedicine(Long id, Medicine medicine);

    void deleteMedicine(Long id);

    Medicine reduceStock(Long medicineId, Integer quantity);

    Medicine restoreStock(Long medicineId, Integer quantity);

}
