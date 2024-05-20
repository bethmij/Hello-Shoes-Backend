package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.InventoryDTO;
import lk.ijse.gdse66.HelloShoes.entity.Inventory;
import lk.ijse.gdse66.HelloShoes.entity.Suppliers;
import lk.ijse.gdse66.HelloShoes.repository.InventoryRepo;
import lk.ijse.gdse66.HelloShoes.repository.SupplierRepo;
import lk.ijse.gdse66.HelloShoes.service.InventoryService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.GenerateID;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    GenerateID generateID;


    @Override
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepo.findAll().stream()
                .filter(inventory -> !inventory.getItemCode().equals("I00-002") && !inventory.getItemCode().equals("I00-003"))
                .map(inventory -> transformer.fromInventoryEntity(inventory))
                .toList();
    }

    @Override
    public InventoryDTO getInventoryDetails(String code) {
        if ("I00-002".equals(code) || "I00-003".equals(code)) {
            throw new NotFoundException("Item Code: " + code + " does not exist");
        }

        if (!inventoryRepo.existsById(code)) {
            throw new NotFoundException("Item Code: " + code + " does not exist");
        }

        return transformer.fromInventoryEntity(inventoryRepo.findById(code).orElseThrow(() ->
                new NotFoundException("Item Code: " + code + " does not exist")));
    }

    @Override
    public InventoryDTO saveInventory(InventoryDTO inventoryDTO) {

//        if(inventoryRepo.existsBySuppliers_SupplierCode(inventoryDTO.getSupplierCode())){
//            throw new NotFoundException("Supplier Code: " + inventoryDTO.getSupplierCode() + " exist");
//        }

        inventoryDTO.setItemCode(generateID.generateItemCode());

        Suppliers suppliers = supplierRepo.findBySupplierCode(inventoryDTO.getSupplierCode());

        Inventory inventory = transformer.toInventoryEntity(inventoryDTO);
        inventory.setSuppliers(suppliers);
        inventory.setSupplierName(suppliers.getSupplierName());

        return transformer.fromInventoryEntity(
                inventoryRepo.save(inventory));


    }

    @Override
    public void updateInventory(InventoryDTO inventoryDTO) {
        if(!inventoryRepo.existsById(inventoryDTO.getItemCode())){
            throw new NotFoundException("Item Code: " + inventoryDTO.getItemCode() + " does not exist");
        }

        inventoryRepo.save(transformer.toInventoryEntity(inventoryDTO));
    }

    @Override
    public void deleteInventory(String id) {
        if(!inventoryRepo.existsById(id)){
            throw new NotFoundException("Item Code: " + id + " does not exist");
        }
        inventoryRepo.deleteById(id);

    }

    @Override
    public List<String> getAllItemCodes() {
        return inventoryRepo.findAllIds().stream()
                .filter(code -> !code.equals("I00-002") && !code.equals("I00-003"))
                .toList();
    }

    @Override
    public String getItemCode() {
        return generateID.generateItemCode();
    }

//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }
}
