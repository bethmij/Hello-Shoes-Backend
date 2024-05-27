package lk.ijse.gdse66.HelloShoes.service;

import lk.ijse.gdse66.HelloShoes.dto.CustomersDTO;

import java.util.List;

public interface CustomerService {
    List<CustomersDTO> getAllCustomers();

    CustomersDTO getCustomersDetails(String code);

    CustomersDTO saveCustomers(CustomersDTO CustomersDTO);

    void updateCustomers(CustomersDTO CustomersDTO);

    void deleteCustomers(String id);

    List<String> getCustomersCodes();

    String getNameByID(String id);

    String getCustomerCode();

    void setLevel();
}
