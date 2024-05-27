package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.RefundDTO;
import lk.ijse.gdse66.HelloShoes.dto.SaleInventoryDTO;
import lk.ijse.gdse66.HelloShoes.dto.SaleServiceDTO;

import java.util.List;

public interface SaleService {
    List<SaleServiceDTO> getAllSaleService();

    SaleServiceDTO getSaleServiceDetails(String code);

    List<SaleInventoryDTO> getSaleInventory(String orderNo);

    SaleServiceDTO saveSaleService(SaleServiceDTO SaleServiceDTO);

    void updateSaleService(SaleServiceDTO SaleServiceDTO);

    void deleteSaleService(String id);

    String getOrderID();

    List<String> getAllOrderCodes();

    void refundItems(RefundDTO refundDTO);
}
