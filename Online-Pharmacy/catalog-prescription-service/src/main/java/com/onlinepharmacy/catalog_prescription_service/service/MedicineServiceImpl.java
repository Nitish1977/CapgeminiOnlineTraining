package com.onlinepharmacy.catalog_prescription_service.service;

import com.onlinepharmacy.catalog_prescription_service.entity.Medicine;
import com.onlinepharmacy.catalog_prescription_service.repository.MedicineRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
public class MedicineServiceImpl implements MedicineService{


    private final MedicineRepository medicineRepository;

    @Override
    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public List<Medicine> getAllMedicine() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Medicine not found with Id: "+id));
    }

    @Override
    public Medicine updateMedicine(Long id, Medicine medicine) {
        Medicine existingMedicine= getMedicineById(id);

        existingMedicine.setName(medicine.getName());
        existingMedicine.setBrand(medicine.getBrand());
        existingMedicine.setCategory(medicine.getCategory());
        existingMedicine.setPrice(medicine.getPrice());
        existingMedicine.setStockQuantity(medicine.getStockQuantity());

        return medicineRepository.save(existingMedicine);
    }

    @Override
    public void deleteMedicine(Long id) {
        Medicine existingMedicine = getMedicineById(id);
        medicineRepository.delete(existingMedicine);

    }

    public Medicine reduceStock(Long medicineId, Integer quantity){
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(()->new RuntimeException("Medicine not found with id: "+medicineId));


        if(medicine.getStockQuantity() < quantity){
            throw new RuntimeException(
                    "Insufficient stock for medicine: "+medicine.getName()

            );
        }
        medicine.setStockQuantity(
                medicine.getStockQuantity() - quantity
        );
        return medicineRepository.save(medicine);
    }

    public Medicine restoreStock(Long medicineId, Integer quantity){

        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(()-> new RuntimeException("Medicine not found with id: "+ medicineId));
        medicine.setStockQuantity(
                medicine.getStockQuantity()+quantity
        );

        return medicineRepository.save(medicine);
    }
}
