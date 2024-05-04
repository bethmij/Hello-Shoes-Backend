package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.SaleInventoryDTO;
import lk.ijse.gdse66.HelloShoes.dto.SaleServiceDTO;
import lk.ijse.gdse66.HelloShoes.entity.*;
import lk.ijse.gdse66.HelloShoes.repository.*;
import lk.ijse.gdse66.HelloShoes.service.SaleService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.GenerateID;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    SaleInventoryRepo saleInventoryRepo;


    @Override
    public List<SaleServiceDTO> getAllSaleService() {
        return saleServiceRepo.findAll().stream()
                .map(saleService -> {

                    Customers customers = customerRepo.findByCustomerName(saleService.getCustomers().getCustomerName());
                    Employee employee = employeeRepo.findByEmployeeName(saleService.getEmployee().getEmployeeName());

                    List<SaleInventoryDTO> saleInventories = saleInventoryRepo.findBySaleService_OrderNo(saleService.getOrderNo())
                            .stream().map(saleInventory -> {

                                SaleInventoryDTO saleInventoryDTO = transformer.fromSaleInventoryEntity(saleInventory);
                                saleInventoryDTO.setItemCode(saleInventory.getInventory().getItemCode());
                                saleInventoryDTO.setOrderID(saleInventory.getSaleService().getOrderNo());
                                return saleInventoryDTO;

                            }).toList();

                    SaleServiceDTO saleServiceDTO = transformer.fromSaleServiceEntity(saleService);
                    saleServiceDTO.setCustomerName(customers.getCustomerName());
                    saleServiceDTO.setCashier(employee.getEmployeeName());
                    saleServiceDTO.setSaleInventory(saleInventories);

                    return saleServiceDTO;
                })
                .toList();
    }

    @Override
    public SaleServiceDTO getSaleServiceDetails(String id) {
        if (!saleServiceRepo.existsById(id)) {
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
        saleServiceRepo.save(saleService);


        for (Map.Entry<String, Integer> entry : saleServiceDTO.getInventoryList().entrySet()) {
            Inventory inventory = inventoryRepo.findById(entry.getKey()).get();
            SaleInventory saleInventory = new SaleInventory();
            saleInventory.setSaleService(saleService);
            saleInventory.setInventory(inventory);
            saleInventory.setSize(entry.getValue());
            saleInventory.setPrize(entry.getValue() * inventory.getSaleUnitPrice());
            saleInventoryRepo.save(saleInventory);
        }

        return transformer.fromSaleServiceEntity(saleService);

    }

    @Override
    public void updateSaleService(SaleServiceDTO saleServiceDTO) {
        if (!saleServiceRepo.existsById(saleServiceDTO.getOrderNo())) {
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
        if (!saleServiceRepo.existsById(id)) {
            throw new NotFoundException("Order no: " + id + " does not exist");
        }
        saleServiceRepo.deleteById(id);

    }

//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }
}
