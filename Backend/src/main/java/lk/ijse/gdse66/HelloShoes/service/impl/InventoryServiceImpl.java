package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.InventoryDTO;
import lk.ijse.gdse66.HelloShoes.dto.ItemSizeDTO;
import lk.ijse.gdse66.HelloShoes.entity.Inventory;
import lk.ijse.gdse66.HelloShoes.entity.ItemSize;
import lk.ijse.gdse66.HelloShoes.entity.Suppliers;
import lk.ijse.gdse66.HelloShoes.repository.InventoryRepo;
import lk.ijse.gdse66.HelloShoes.repository.ItemSizeRepo;
import lk.ijse.gdse66.HelloShoes.repository.SupplierRepo;
import lk.ijse.gdse66.HelloShoes.service.InventoryService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.GenerateID;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import lk.ijse.gdse66.HelloShoes.service.util.enums.ItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Autowired
    ItemSizeRepo itemSizeRepo;


    @Override
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepo.findAll().stream()
//                .filter(inventory -> !inventory.getItemCode().equals("I00-005"))
//                .filter(inventory -> !inventory.getStatus().equals("NOT_AVAILABLE"))
                .map(inventory -> {
                            InventoryDTO inventoryDTO = transformer.fromInventoryEntity(inventory);
                            Suppliers supplier = supplierRepo.findBySupplierName(inventoryDTO.getSupplierName());
                            inventoryDTO.setSupplierCode(supplier.getSupplierCode());
                            return inventoryDTO;
                        }
                ).toList();
    }

    @Override
    public InventoryDTO getInventoryDetails(String code) {
//        if ("I00-005".equals(code)) {
//            throw new NotFoundException("Item Code: " + code + " does not exist");
//        }

        if (!inventoryRepo.existsById(code)) {
            throw new NotFoundException("Item Code: " + code + " does not exist");
        }

        InventoryDTO inventoryDTO = transformer.fromInventoryEntity(inventoryRepo.findById(code).get());
        Suppliers supplier = supplierRepo.findBySupplierName(inventoryDTO.getSupplierName());
        inventoryDTO.setSupplierCode(supplier.getSupplierCode());
        return inventoryDTO;

    }

    @Override
    public InventoryDTO saveInventory(InventoryDTO inventoryDTO) {

//        if(inventoryRepo.existsBySuppliers_SupplierCode(inventoryDTO.getSupplierCode())){
//            throw new NotFoundException("Supplier Code: " + inventoryDTO.getSupplierCode() + " exist");
//        }

//        inventoryDTO.setItemCode(generateID.generateItemCode());

        Suppliers suppliers = supplierRepo.findBySupplierCode(inventoryDTO.getSupplierCode());

        Inventory inventory = transformer.toInventoryEntity(inventoryDTO);
        inventory.setSuppliers(suppliers);
        inventory.setSupplierName(suppliers.getSupplierName());


        setItemStatus();
        setItemQuantity();

        int total=0;
        for (Map.Entry<String, Integer> entry : inventoryDTO.getItemSizeList().entrySet()) {
            total+=entry.getValue();
        }
        inventory.setItemQty(total);
        inventoryRepo.save(inventory);


        for (Map.Entry<String, Integer> entry : inventoryDTO.getItemSizeList().entrySet()) {
            ItemSize itemSize = new ItemSize(entry.getKey(), entry.getValue());
            itemSize.setItem(inventory);

            itemSizeRepo.save(itemSize);
        }

        return transformer.fromInventoryEntity(inventory);


    }

    @Override
    public void updateInventory(InventoryDTO inventoryDTO) {
        if (!inventoryRepo.existsById(inventoryDTO.getItemCode())) {
            throw new NotFoundException("Item Code: " + inventoryDTO.getItemCode() + " does not exist");
        }

        Suppliers suppliers = supplierRepo.findBySupplierCode(inventoryDTO.getSupplierCode());

        Inventory inventory = transformer.toInventoryEntity(inventoryDTO);
        inventory.setSuppliers(suppliers);
        inventory.setSupplierName(suppliers.getSupplierName());
        setItemStatus();
        setItemQuantity();
        inventoryRepo.save(inventory);
    }

    @Override
    public void deleteInventory(String id) {
        if (!inventoryRepo.existsById(id)) {
            throw new NotFoundException("Item Code: " + id + " does not exist");
        }
        inventoryRepo.deleteById(id);

    }

    @Override
    public List<String> getAllItemCodes() {
        return inventoryRepo.findAllIds().stream()
//                .filter(code -> !code.equals("I00-005"))
//                .filter(code -> !code.getStatus().equals("NOT_AVAILABLE"))

                .toList();
    }

    @Override
    public String getItemCode() {
//        return generateID.generateItemCode();
        return null;
    }

    @Override
    public List<InventoryDTO> getLowStockItem() {
        return inventoryRepo.findLowStock().stream()
                .map(item -> transformer.fromInventoryEntity(item))
                .toList();
    }

    @Override
    public void setItemStatus() {
        List<Inventory> inventories = inventoryRepo.findAll().stream()
                .map(inventory -> {
                            if (inventory.getItemQty() == 0) {
                                inventory.setStatus(ItemStatus.NOT_AVAILABLE);
                            } else if (inventory.getItemQty() <= 10) {
                                inventory.setStatus(ItemStatus.LOW);
                            } else {
                                inventory.setStatus(ItemStatus.AVAILABLE);
                            }
                            inventoryRepo.save(inventory);

                            return inventory;
                        }
                ).toList();
    }

    @Override
    public void setItemQuantity() {
        List<Inventory> inventories = inventoryRepo.findAll().stream()
                .map(inventory -> {
                            int totalQty = 0;
                            List<ItemSize> item = itemSizeRepo.findByItem_ItemCode(inventory.getItemCode());
                            for (ItemSize itemSize : item) {
                                totalQty += itemSize.getQuantity();
                            }
                            inventory.setItemQty(totalQty);
                            inventoryRepo.save(inventory);
                            return inventory;
                        }
                ).toList();


    }

    @Override
    public List<ItemSizeDTO> getItemSizes(String itemCode) {
        return itemSizeRepo.findByItem_ItemCode(itemCode).stream()
                .map(itemSizes -> transformer.fromItemSizes(itemSizes))
                .toList();
    }


//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }
}
