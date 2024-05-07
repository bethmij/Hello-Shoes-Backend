package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.CustomersDTO;
import lk.ijse.gdse66.HelloShoes.repository.CustomerRepo;
import lk.ijse.gdse66.HelloShoes.service.CustomerService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.GenerateID;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    GenerateID generateID;


    @Override
    public List<CustomersDTO> getAllCustomers() {
        return customerRepo.findAll().stream()
                .map(customers -> transformer.fromCustomerEntity(customers))
                .toList();
    }

    @Override
    public CustomersDTO getCustomersDetails(String code) {
        if(!customerRepo.existsById(code)){
            throw new NotFoundException("Customer Code: " + code + " does not exist");
        }

        return transformer.fromCustomerEntity(customerRepo.findById(code).get());

    }

    @Override
    public CustomersDTO saveCustomers(CustomersDTO customersDTO) {
        customersDTO.setCustomerCode(generateID.generateCustomerCode());

        return transformer.fromCustomerEntity(
                customerRepo.save(
                        transformer.toCustomerEntity(customersDTO)));
    }

    @Override
    public void updateCustomers(CustomersDTO customersDTO) {
        if(!customerRepo.existsById(customersDTO.getCustomerCode())){
            throw new NotFoundException("Customer Code: " + customersDTO.getCustomerCode() + " does not exist");
        }

        customerRepo.save(transformer.toCustomerEntity(customersDTO));
    }

    @Override
    public void deleteCustomers(String id) {
        if(!customerRepo.existsById(id)){
            throw new NotFoundException("Customer Code: " + id + " does not exist");
        }
        customerRepo.deleteById(id);

    }

    @Override
    public List<String> getCustomersCodes() {
        return customerRepo.findAllIds();
    }

    @Override
    public String getNameByID(String id) {
        return customerRepo.findById(id).get().getCustomerName();
    }

    @Override
    public String getCustomerCode() {
        return generateID.generateCustomerCode();
    }

//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }
}
