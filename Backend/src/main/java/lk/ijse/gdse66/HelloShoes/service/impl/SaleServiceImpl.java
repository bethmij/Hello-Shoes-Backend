package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.*;
import lk.ijse.gdse66.HelloShoes.entity.*;
import lk.ijse.gdse66.HelloShoes.repository.*;
import lk.ijse.gdse66.HelloShoes.service.SaleService;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.exception.ServiceException;
import lk.ijse.gdse66.HelloShoes.service.util.GenerateID;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
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
    AdminPanelRepo adminPanelRepo;

    @Autowired
    SaleInventoryRepo saleInventoryRepo;

    @Autowired
    InventoryServiceImpl inventoryService;

    @Autowired
    SupplierRepo supplierRepo;


    @Override
    public List<SaleServiceDTO> getAllSaleService() {
        return saleServiceRepo.findAll().stream()
                .map(saleService -> {

                    Customers customers = customerRepo.findByCustomerName(saleService.getCustomers().getCustomerName());
                    Employee employee = employeeRepo.findByEmployeeName(saleService.getEmployee().getEmployeeName());

                    SaleServiceDTO saleServiceDTO = transformer.fromSaleServiceEntity(saleService);
                    saleServiceDTO.setCustomerName(customers.getCustomerName());
                    saleServiceDTO.setCashier(employee.getEmployeeName());

                    return saleServiceDTO;
                })
                .toList();
    }

    @Override
    public SaleServiceDTO getSaleServiceDetails(String id) {
        if (!saleServiceRepo.existsById(id)) {
            throw new NotFoundException("Order no: " + id + " does not exist");
        }

        SaleServiceEntity saleServiceEntity = saleServiceRepo.findById(id).get();
        Customers customers = customerRepo.findByCustomerName(saleServiceEntity.getCustomers().getCustomerName());
        Employee employee = employeeRepo.findByEmployeeName(saleServiceEntity.getEmployee().getEmployeeName());

        SaleServiceDTO saleServiceDTO = transformer.fromSaleServiceEntity(saleServiceEntity);
        saleServiceDTO.setCustomerCode(customers.getCustomerCode());
        saleServiceDTO.setCustomerName(customers.getCustomerName());
        saleServiceDTO.setEmployeeCode(employee.getEmployeeCode());
        saleServiceDTO.setCashier(employee.getEmployeeName());
        return saleServiceDTO;

    }

    @Override
    public List<SaleInventoryDTO> getSaleInventory(String orderNo) {

        SaleServiceEntity saleService = saleServiceRepo.findById(orderNo).get();

        return saleInventoryRepo.findBySaleService_OrderID(saleService.getOrderID())
                .stream().map(saleInventory -> {

                    SaleInventoryDTO saleInventoryDTO = transformer.fromSaleInventoryEntity(saleInventory);
                    saleInventoryDTO.setItemCode(saleInventory.getInventory().getItemCode());
                    saleInventoryDTO.setOrderID(saleInventory.getSaleService().getOrderID());
                    saleInventoryDTO.setSize(saleInventory.getInventory().getSize());
                    saleInventoryDTO.setSaleUnitPrice(saleInventory.getInventory().getSaleUnitPrice());
                    saleInventoryDTO.setItemDesc(saleInventory.getInventory().getItemDesc());
                    saleInventoryDTO.setItemQuantity(saleInventory.getQty());
                    return saleInventoryDTO;
                }).toList();
    }

    @Override
    @Transactional
    public SaleServiceDTO saveSaleService(SaleServiceDTO saleServiceDTO) {

        saleServiceDTO.setOrderID(generateID.generateSaleCode());

        SaleServiceEntity saleService = saveOrder(saleServiceDTO);

        inventoryService.setItemStatus();

        return transformer.fromSaleServiceEntity(saleService);


    }


    @Override
    public void updateSaleService(SaleServiceDTO saleServiceDTO) {
        if (!saleServiceRepo.existsById(saleServiceDTO.getOrderID())) {
            throw new NotFoundException("Order no: " + saleServiceDTO.getOrderID() + " does not exist");
        }
        inventoryService.setItemStatus();

        SaleServiceEntity saleService = saleServiceRepo.findById(saleServiceDTO.getOrderID()).get();
        saleServiceDTO.setPurchaseDate(saleService.getPurchaseDate());
        saveOrder(saleServiceDTO);

    }

    @Override
    public void deleteSaleService(String id) {
        if (!saleServiceRepo.existsById(id)) {
            throw new NotFoundException("Order no: " + id + " does not exist");
        }
        saleServiceRepo.deleteById(id);

    }

    @Override
    public String getOrderID() {
        return generateID.generateSaleCode();
    }

    @Override
    public List<String> getAllOrderCodes() {
        return saleServiceRepo.findAllIds();
    }

    @Override
    public void refundItems(RefundDTO refundDTO) {
        Inventory inventory = inventoryRepo.findById(refundDTO.getItemCode()).get();
        SaleServiceEntity order = saleServiceRepo.findById(refundDTO.getOrderID()).get();

        order.setTotalPrice(order.getTotalPrice() - inventory.getSaleUnitPrice());
        saleServiceRepo.save(order);


        LocalDate purchaseDate = convertToLocalDate(refundDTO.getPurchaseDate());
        SaleInventory saleInventory = saleInventoryRepo.findSalesByOrderAndPurchaseDate(refundDTO.getOrderID(), purchaseDate);


        if(saleInventory != null) {
            if (saleInventory.getQty() >= refundDTO.getItemQty()) {
                saleInventory.setQty(saleInventory.getQty() - refundDTO.getItemQty());
                saleInventory.setPurchase_data(refundDTO.getPurchaseDate());
                saleInventoryRepo.save(saleInventory);
                inventoryService.setItemStatus();
            } else {
                throw new ServiceException("Insufficient item for refund: " + inventory.getItemCode());
            }
        }else {
            throw new ServiceException("No such order");
        }
    }

//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }


    private SaleServiceEntity saveOrder(SaleServiceDTO saleServiceDTO) {

        SaleServiceEntity saleService = transformer.toSaleServiceEntity(saleServiceDTO);

        if(!saleServiceDTO.getCustomerName().isEmpty()) {
            Customers customers = customerRepo.findByCustomerName(saleServiceDTO.getCustomerName());
            if (customers == null) {
                throw new ServiceException("Customer not found: " + saleServiceDTO.getCustomerName());
            }
            saleService.setCustomers(customers);

            if(saleServiceDTO.getTotalPrice() >= 800){
                customers.setTotalPoints(customers.getTotalPoints()+1);
                saleService.setAddedPoints(1);
            }
            customerRepo.save(customers);
        }


        Employee employee = employeeRepo.findByEmployeeName(saleServiceDTO.getCashier());
        if (employee == null) {
            throw new ServiceException("Employee not found: " + saleServiceDTO.getCashier());
        }

        saleService.setEmployee(employee);
        saleServiceRepo.save(saleService);

        int totalSales = 0;
        String mostSaleItem = saleServiceDTO.getInventoryList().keySet().iterator().next();
        int mostSaleQty = 0;

        for (Map.Entry<String, Integer> entry : saleServiceDTO.getInventoryList().entrySet()) {
            Inventory inventory = inventoryRepo.findById(entry.getKey()).orElse(null);
            if (inventory == null) {
                throw new ServiceException("Inventory not found: " + entry.getKey());
            }

            SaleInventory saleInventory = new SaleInventory();
            saleInventory.setSaleService(saleService);
            saleInventory.setInventory(inventory);
            saleInventory.setQty(entry.getValue());
            saleInventory.setPrize(entry.getValue() * inventory.getSaleUnitPrice());
            saleInventoryRepo.save(saleInventory);

            int updatedQty = inventory.getItemQty() - entry.getValue();
            if (updatedQty < 0) {
                throw new ServiceException("Insufficient inventory for item: " + inventory.getItemCode());
            }

            InventoryDTO inventoryDTO = transformer.fromInventoryEntity(inventory);
            inventoryDTO.setItemQty(updatedQty);

            Suppliers suppliers = supplierRepo.findBySupplierName(inventoryDTO.getSupplierName());
            if (suppliers == null) {
                throw new ServiceException("Supplier not found: " + inventoryDTO.getSupplierName());
            }
            inventoryDTO.setSupplierCode(suppliers.getSupplierCode());
            inventoryService.updateInventory(inventoryDTO);
        }

        LocalDate today = LocalDate.now();
        Date todayAsDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        AdminPanel adminPanel = adminPanelRepo.findByDate(todayAsDate);

        AdminPanelDTO adminPanelDTO = new AdminPanelDTO();

        adminPanelDTO.setDate(new java.sql.Date(todayAsDate.getTime()));


        Map<String, Integer> itemQuantityMap = new HashMap<>();

        // Calculate totalSales and mostSaleItemQty from saleInventoryList
        List<SaleInventory> saleInventoryList = saleInventoryRepo.findByPurchase_data(todayAsDate);
        for (SaleInventory saleInventory : saleInventoryList) {
            String item = saleInventory.getInventory().getItemCode();
            int quantity = saleInventory.getQty();
            itemQuantityMap.put(item, itemQuantityMap.getOrDefault(item, 0) + quantity);


            totalSales += quantity;
            if (quantity > mostSaleQty) {
                mostSaleQty = quantity;
                mostSaleItem = item;
            }
        }

        // Calculate totalSales and mostSaleItemQty from saleServiceDTO.getInventoryList()
        for (Map.Entry<String, Integer> entry : saleServiceDTO.getInventoryList().entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            itemQuantityMap.put(item, itemQuantityMap.getOrDefault(item, 0) + quantity);

            // Update totalSales and mostSaleItemQty
            totalSales += quantity;
            if (quantity > mostSaleQty) {
                mostSaleQty = quantity;
                mostSaleItem = item;
            }
        }

        adminPanelDTO.setTotalSales(totalSales);
        adminPanelDTO.setMostSaleItemQty(itemQuantityMap.getOrDefault(mostSaleItem, mostSaleQty));
        adminPanelDTO.setMostSaleItem(mostSaleItem);

        if (adminPanel == null) {
            adminPanelDTO.setTotalProfit(saleServiceDTO.getTotalPrice());
            adminPanel = transformer.toAdminPanelEntity(adminPanelDTO);


        } else {

            adminPanel.setInventory(new Inventory(adminPanelDTO.getMostSaleItem()));
            adminPanel.setMostSaleItemQty(adminPanelDTO.getMostSaleItemQty());
            adminPanel.setTotalSales(adminPanelDTO.getTotalSales());
            adminPanel.setTotalProfit(adminPanel.getTotalProfit() + saleServiceDTO.getTotalPrice());
        }

        adminPanelRepo.save(adminPanel);



        return saleService;
    }

    private LocalDate convertToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
