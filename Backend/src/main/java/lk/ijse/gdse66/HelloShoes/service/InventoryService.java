package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.InventoryDTO;
import lk.ijse.gdse66.HelloShoes.dto.ItemSizeDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventory();

    InventoryDTO getInventoryDetails(String code);

    InventoryDTO saveInventory(InventoryDTO InventoryDTO);

    void updateInventory(InventoryDTO InventoryDTO);

    void deleteInventory(String id);

    List<String> getAllItemCodes();

    String getItemCode();

    List<InventoryDTO> getLowStockItem();

    void setItemStatus();

    void setItemQuantity();

    List<ItemSizeDTO> getItemSizes(String itemCode);
}
