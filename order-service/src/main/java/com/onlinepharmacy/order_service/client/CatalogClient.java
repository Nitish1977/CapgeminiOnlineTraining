package com.onlinepharmacy.order_service.client;


import com.onlinepharmacy.order_service.dto.MedicineResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "catalog-prescription-service")
public interface CatalogClient {

    @GetMapping("/api/medicine/findMedicine/{id}")
    MedicineResponse getMedicineById(@PathVariable Long id);

    @PutMapping("/api/medicine/reduceStock/{id}")
    MedicineResponse reduceStock(
            @PathVariable("id") Long id, @RequestParam("quantity") Integer quantity
    );

    @PutMapping("/api/medicine/restoreStock/{id}")
    MedicineResponse restoreStock(@PathVariable Long id, @RequestParam("quantity") Integer quantity);

}
