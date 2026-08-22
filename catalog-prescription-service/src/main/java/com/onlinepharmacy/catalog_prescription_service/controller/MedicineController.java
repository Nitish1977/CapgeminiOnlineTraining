package com.onlinepharmacy.catalog_prescription_service.controller;


import com.onlinepharmacy.catalog_prescription_service.entity.Medicine;
import com.onlinepharmacy.catalog_prescription_service.service.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.bouncycastle.crypto.agreement.jpake.JPAKEUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/api/medicine")
public class MedicineController {

    private final MedicineService medicineService;


    @Operation(summary = "add medicines",
           security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addMedicine")
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine){
        Medicine saveMedicine = medicineService.addMedicine(medicine);
        return new ResponseEntity<>(saveMedicine, HttpStatus.CREATED);
    }
    @Operation(summary = "All Medicines")
    @GetMapping("/allMedicine")
    public ResponseEntity<List<Medicine>> getMedicine(){
        return ResponseEntity.ok(medicineService.getAllMedicine());
    }


    @Operation(summary = "Find Medicine By ID")
    @GetMapping("/findMedicine/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id){

        return ResponseEntity.ok(medicineService.getMedicineById(id));
    }

    @Operation(
            summary = "Update medicines",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public  ResponseEntity<Medicine> updateMedicine(@PathVariable Long id, @RequestBody Medicine medicine){
        return  ResponseEntity.ok(medicineService.updateMedicine(id, medicine));
    }

    @Operation(
            summary = "Delete Medicine",
           security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable Long id){
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok("Medicine deleted successfully");
    }

    @Operation(
            summary = "reduce stock",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/reduceStock/{id}")
    public ResponseEntity<Medicine> reduceStock(@PathVariable Long id, @RequestParam Integer quantity){
        return ResponseEntity.ok(medicineService.reduceStock(id, quantity));
    }


    @Operation(
            summary = "restore stock",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/restoreStock/{id}")
    public ResponseEntity<Medicine> restoreStock(@PathVariable Long id, @RequestParam Integer quantity){
        return ResponseEntity.ok(medicineService.restoreStock(id,quantity));
    }


}
