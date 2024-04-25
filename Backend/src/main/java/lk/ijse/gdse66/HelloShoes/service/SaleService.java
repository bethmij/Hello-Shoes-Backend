package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.SaleServiceDTO;

import java.util.List;

public interface SaleService {
    List<SaleServiceDTO> getAllSaleService();

    SaleServiceDTO getSaleServiceDetails(String code);

    SaleServiceDTO saveSaleService(SaleServiceDTO SaleServiceDTO);

    void updateSaleService(SaleServiceDTO SaleServiceDTO);

    void deleteSaleService(String id);

//    List<String> GetSupplierCode();
}
