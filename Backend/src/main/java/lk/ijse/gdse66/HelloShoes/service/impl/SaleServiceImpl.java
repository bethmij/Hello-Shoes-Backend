package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.SaleServiceDTO;
import lk.ijse.gdse66.HelloShoes.entity.*;
import lk.ijse.gdse66.HelloShoes.entity.embeddedID.SaleItemID;
import lk.ijse.gdse66.HelloShoes.repository.CustomerRepo;
import lk.ijse.gdse66.HelloShoes.repository.EmployeeRepo;
import lk.ijse.gdse66.HelloShoes.repository.InventoryRepo;
import lk.ijse.gdse66.HelloShoes.repository.SaleServiceRepo;
import lk.ijse.gdse66.HelloShoes.service.SaleService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.util.GenerateID;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    InventoryRepo inventoryRepo;

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
    @Transactional
    public SaleServiceDTO saveSaleService(SaleServiceDTO saleServiceDTO) {

        saleServiceDTO.setOrderNo(generateID.generateSaleCode());
        SaleServiceEntity saleService = saveSale(saleServiceDTO);

        return transformer.fromSaleServiceEntity(
                saleServiceRepo.save(saleService));

    }

    @Override
    public void updateSaleService(SaleServiceDTO saleServiceDTO) {
        if(!saleServiceRepo.existsById(saleServiceDTO.getOrderNo())){
            throw new NotFoundException("Order no: " + saleServiceDTO.getOrderNo() + " does not exist");
        }

        SaleServiceEntity saleService = saveSale(saleServiceDTO);

        saleServiceRepo.save(saleService);
    }

    @Override
    public void deleteSaleService(String id) {
        if(!saleServiceRepo.existsById(id)){
            throw new NotFoundException("Order no: " + id + " does not exist");
        }
        saleServiceRepo.deleteById(id);

    }

    private SaleServiceEntity saveSale(SaleServiceDTO saleServiceDTO) {
        SaleServiceEntity saleService = transformer.toSaleServiceEntity(saleServiceDTO);
        Customers customers = customerRepo.findByCustomerName(saleServiceDTO.getCustomerName());
        Employee employee = employeeRepo.findByEmployeeName(saleServiceDTO.getCashier());
        Set<SaleItem> saleItems = new HashSet<>();

        for (Map.Entry<String, Integer> entry : saleServiceDTO.getInventoryList().entrySet()) {
            String itemCode = entry.getKey();
            int quantity = entry.getValue();

            Inventory inventory = inventoryRepo.findById(itemCode).get();
            SaleItem saleItem = new SaleItem();
            saleItem.setSaleItemID(new SaleItemID(saleServiceDTO.getOrderNo(), inventory.getItemCode()));
            saleItem.setSaleService(saleService);
            saleItem.setInventory(inventory);
            saleItem.setItemQty(quantity);
            saleItem.setTotalPrice(inventory.getSaleUnitPrice()*saleItem.getItemQty());
            saleItems.add(saleItem);
        }

        saleService.setEmployee(employee);
        saleService.setCustomers(customers);
        saleService.setSaleItems(saleItems);
        return saleService;
    }

}
