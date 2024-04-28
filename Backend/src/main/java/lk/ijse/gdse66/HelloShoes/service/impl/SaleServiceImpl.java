package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.SaleServiceDTO;
import lk.ijse.gdse66.HelloShoes.entity.Customers;
import lk.ijse.gdse66.HelloShoes.entity.Employee;
import lk.ijse.gdse66.HelloShoes.entity.SaleServiceEntity;
import lk.ijse.gdse66.HelloShoes.repository.CustomerRepo;
import lk.ijse.gdse66.HelloShoes.repository.EmployeeRepo;
import lk.ijse.gdse66.HelloShoes.repository.SaleServiceRepo;
import lk.ijse.gdse66.HelloShoes.service.SaleService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.GenerateID;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    @Autowired
    SaleServiceRepo saleServiceRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    GenerateID generateID;


    @Override
    public List<SaleServiceDTO> getAllSaleService() {
        return saleServiceRepo.findAll().stream()
                .map(saleService -> transformer.fromSaleServiceEntity(saleService))
                .toList();
    }

    @Override
    public SaleServiceDTO getSaleServiceDetails(String id) {
        if(!saleServiceRepo.existsById(id)){
            throw new NotFoundException("Order no: " + id + " does not exist");
        }

        return transformer.fromSaleServiceEntity(saleServiceRepo.findById(id).get());

    }

    @Override
    public SaleServiceDTO saveSaleService(SaleServiceDTO saleServiceDTO) {

        saleServiceDTO.setOrderNo(generateID.generateSaleCode());

        Customers customers = customerRepo.findByCustomerName(saleServiceDTO.getCustomerName());
        Employee employee = employeeRepo.findByEmployeeName(saleServiceDTO.getCashier());

        SaleServiceEntity saleService = transformer.toSaleServiceEntity(saleServiceDTO);
        saleService.setEmployee(employee);
        saleService.setCustomers(customers);


        return transformer.fromSaleServiceEntity(
                saleServiceRepo.save(saleService));

    }

    @Override
    public void updateSaleService(SaleServiceDTO saleServiceDTO) {
        if(!saleServiceRepo.existsById(saleServiceDTO.getOrderNo())){
            throw new NotFoundException("Order no: " + saleServiceDTO.getOrderNo() + " does not exist");
        }

        Customers customers = customerRepo.findByCustomerName(saleServiceDTO.getCustomerName());
        Employee employee = employeeRepo.findByEmployeeName(saleServiceDTO.getCashier());

        SaleServiceEntity saleService = transformer.toSaleServiceEntity(saleServiceDTO);
        saleService.setEmployee(employee);
        saleService.setCustomers(customers);

        saleServiceRepo.save(saleService);
    }

    @Override
    public void deleteSaleService(String id) {
        if(!saleServiceRepo.existsById(id)){
            throw new NotFoundException("Order no: " + id + " does not exist");
        }
        saleServiceRepo.deleteById(id);

    }

//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }
}
