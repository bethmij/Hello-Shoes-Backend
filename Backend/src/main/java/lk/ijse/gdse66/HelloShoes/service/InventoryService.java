package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventory();

    InventoryDTO getInventoryDetails(String code);

    InventoryDTO saveInventory(InventoryDTO InventoryDTO);

    void updateInventory(InventoryDTO InventoryDTO);

    void deleteInventory(String id);

    List<String> getAllItemCodes();

    String getItemCode();
}
