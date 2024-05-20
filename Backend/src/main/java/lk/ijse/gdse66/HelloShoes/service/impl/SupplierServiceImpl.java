package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.SuppliersDTO;
import lk.ijse.gdse66.HelloShoes.repository.SupplierRepo;
import lk.ijse.gdse66.HelloShoes.service.SupplierService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.GenerateID;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    GenerateID generateID;


    @Override
    public List<SuppliersDTO> getAllSuppliers() {
        return supplierRepo.findAll().stream()
                .map(suppliers -> transformer.fromSupplierEntity(suppliers))
                .toList();
    }

    @Override
    public SuppliersDTO getSupplierDetails(String code) {
        if(!supplierRepo.existsById(code)){
            throw new NotFoundException("Supplier Code: " + code + " does not exist");
        }

        return transformer.fromSupplierEntity(supplierRepo.findById(code).get());

    }

    @Override
    public SuppliersDTO saveSupplier(SuppliersDTO suppliersDTO) {
        suppliersDTO.setSupplierCode(generateID.generateSupplerCode());

        return transformer.fromSupplierEntity(
                supplierRepo.save(
                        transformer.toSupplierEntity(suppliersDTO)));
    }

    @Override
    public void updateSupplier(SuppliersDTO suppliersDTO) {
        if(!supplierRepo.existsById(suppliersDTO.getSupplierCode())){
            throw new NotFoundException("Supplier Code: " + suppliersDTO.getSupplierCode() + " does not exist");
        }

        supplierRepo.save(transformer.toSupplierEntity(suppliersDTO));
    }

    @Override
    public void deleteSupplier(String id) {
        if(!supplierRepo.existsById(id)){
            throw new NotFoundException("Supplier Code: " + id + " does not exist");
        }
        supplierRepo.deleteById(id);

    }

    @Override
    public String getSupplierCode() {
        return generateID.generateSupplerCode();

    }

//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }
}
