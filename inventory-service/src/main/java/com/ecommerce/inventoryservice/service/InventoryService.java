package com.ecommerce.inventoryservice.service;


import com.ecommerce.inventoryservice.model.Inventory;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode)
    {
        Optional<Inventory>inventoryOptional=inventoryRepository.findBySkuCode(skuCode);
        if(inventoryOptional!=null && inventoryOptional.isPresent())
        {
            log.info("skuCode is present");
            return true;
        }
        else
        {
            log.info("skuCode is not present");
            return false;

        }
    }

}
