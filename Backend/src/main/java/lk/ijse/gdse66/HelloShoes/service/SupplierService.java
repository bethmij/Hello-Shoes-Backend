package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.SuppliersDTO;

import java.util.List;

public interface SupplierService {
    List<SuppliersDTO> getAllSuppliers();

    SuppliersDTO getSupplierDetails(String code);

    SuppliersDTO saveSupplier(SuppliersDTO suppliersDTO);

    void updateSupplier(SuppliersDTO suppliersDTO);

    void deleteSupplier(String id);

    String getSupplierCode();

    List<String> getSupplierCodes();

//    List<String> GetSupplierCode();
}
