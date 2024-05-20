package lk.ijse.gdse66.HelloShoes.service.impl;

import lk.ijse.gdse66.HelloShoes.dto.AdminPanelDTO;
import lk.ijse.gdse66.HelloShoes.dto.SaleInventoryDTO;
import lk.ijse.gdse66.HelloShoes.dto.SaleServiceDTO;
import lk.ijse.gdse66.HelloShoes.entity.*;
import lk.ijse.gdse66.HelloShoes.repository.*;
import lk.ijse.gdse66.HelloShoes.service.SaleService;
//import lk.ijse.gdse66.HelloShoes.service.exception.InsufficientInventoryException;
import lk.ijse.gdse66.HelloShoes.service.exception.NotFoundException;
import lk.ijse.gdse66.HelloShoes.service.exception.ServiceException;
import lk.ijse.gdse66.HelloShoes.service.util.GenerateID;
import lk.ijse.gdse66.HelloShoes.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public SaleServiceDTO saveSaleService(SaleServiceDTO saleServiceDTO) {

        saleServiceDTO.setOrderID(generateID.generateSaleCode());

        Customers customers = customerRepo.findByCustomerName(saleServiceDTO.getCustomerName());
        Employee employee = employeeRepo.findByEmployeeName(saleServiceDTO.getCashier());

        SaleServiceEntity saleService = transformer.toSaleServiceEntity(saleServiceDTO);
        saleService.setEmployee(employee);
        saleService.setCustomers(customers);
        saleServiceRepo.save(saleService);

        int totalSales = 0;
        String mostSaleItem = saleServiceDTO.getInventoryList().keySet().iterator().next();
        int mostSaleQty = 0;

        for (Map.Entry<String, Integer> entry : saleServiceDTO.getInventoryList().entrySet()) {
            Inventory inventory = inventoryRepo.findById(entry.getKey()).get();
            System.out.println(inventory);
            SaleInventory saleInventory = new SaleInventory();
            saleInventory.setSaleService(saleService);
            saleInventory.setInventory(inventory);
            saleInventory.setQty(entry.getValue());
            saleInventory.setPrize(entry.getValue() * inventory.getSaleUnitPrice());
            saleInventoryRepo.save(saleInventory);

            int updatedQty = inventory.getItemQty() - entry.getValue();
            if (updatedQty < 0) {
//                throw new InsufficientInventoryException("Insufficient inventory for item: " + inventory.getItemCode());
                throw new ServiceException("Insufficient inventory for item: " + inventory.getItemCode());
            }
            inventory.setItemQty(updatedQty);
            inventoryRepo.save(inventory);
        }
//
//        LocalDate today = LocalDate.now();
//        Date todayAsDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        AdminPanel adminPanel = adminPanelRepo.findByDate(todayAsDate);
//        AdminPanelDTO adminPanelDTO = new AdminPanelDTO();
//        adminPanelDTO.setTotalProfit(saleServiceDTO.getTotalPrice());
//        adminPanelDTO.setDate( new java.sql.Date(todayAsDate.getTime()) );
//
//        if (adminPanel == null){
//            adminPanelDTO.setTotalSales(totalSales);
//            adminPanelDTO.setMostSaleItemQty(mostSaleQty);
//            adminPanelDTO.setMostSaleItem(mostSaleItem);
//        }else {
//            totalSales = 0;
//            List<SaleInventory> saleInventoryList = saleInventoryRepo.findByPurchase_data(todayAsDate);
//
//            Map<String, Integer> itemQuantityMap = new HashMap<>();
//
//            for (SaleInventory saleInventory : saleInventoryList) {
//                String item = saleInventory.getInventory().getItemCode(); // Assuming there's a method to get the item name
//                int quantity = saleInventory.getQty();
//                itemQuantityMap.put(item, itemQuantityMap.getOrDefault(item, 0) + quantity);
//            }
//
//            for (Map.Entry<String, Integer> entry : saleServiceDTO.getInventoryList().entrySet()) {
//                String item = entry.getKey();
//                int quantity = entry.getValue();
//                itemQuantityMap.put(item, itemQuantityMap.getOrDefault(item, 0) + quantity);
//            }
//            for (Map.Entry<String, Integer> entry : itemQuantityMap.entrySet()) {
//                String item = entry.getKey();
//                int quantity = entry.getValue();
//
//                // Update totalSales with the total quantity of all items
//                totalSales += quantity;
//
//                // Check if the current item has a higher quantity than the previously found maximum
//                if (quantity > mostSaleQty) {
//                    mostSaleQty = quantity;
//                    mostSaleItem = item;
//                }
//            }
//
//            adminPanelDTO.setTotalSales(totalSales);
//            adminPanelDTO.setMostSaleItemQty(mostSaleQty);
//            adminPanelDTO.setMostSaleItem(mostSaleItem);
//
//
////            System.out.println("Total Sales: " + totalSales);
////            System.out.println("Most Sale Item: " + mostSaleItem);
////            System.out.println("Most Sale Item Quantity: " + mostSaleQty);
//
//        }
//
//        adminPanel = transformer.toAdminPanelEntity(adminPanelDTO); // Assuming transformer.toAdminPanelEntity converts AdminPanelDTO to AdminPanel entity
//        adminPanelRepo.save(adminPanel);
//
//
////        AdminPanelDTO adminPanelDTO = new AdminPanelDTO();
////        adminPanelDTO.setTotalSales(totalSales);
////        adminPanelDTO.setTotalProfit();

//        return transformer.fromSaleServiceEntity(saleService);
        LocalDate today = LocalDate.now();
        Date todayAsDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        AdminPanel adminPanel = adminPanelRepo.findByDate(todayAsDate);

        AdminPanelDTO adminPanelDTO = new AdminPanelDTO();

        adminPanelDTO.setDate(new java.sql.Date(todayAsDate.getTime()) );


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

        return transformer.fromSaleServiceEntity(saleService);
//        return saleServiceDTO;

    }

    @Override
    public void updateSaleService(SaleServiceDTO saleServiceDTO) {
        if (!saleServiceRepo.existsById(saleServiceDTO.getOrderID())) {
            throw new NotFoundException("Order no: " + saleServiceDTO.getOrderID() + " does not exist");
        }

        Customers customers = customerRepo.findByCustomerName(saleServiceDTO.getCustomerName());
        Employee employee = employeeRepo.findByEmployeeName(saleServiceDTO.getCashier());
        SaleServiceEntity saleServiceEntity = saleServiceRepo.findById(saleServiceDTO.getOrderID()).get();

        SaleServiceEntity saleService = transformer.toSaleServiceEntity(saleServiceDTO);
        saleService.setEmployee(employee);
        saleService.setCustomers(customers);
        saleService.setPurchaseDate(saleServiceEntity.getPurchaseDate());
        saleServiceRepo.save(saleService);


        for (Map.Entry<String, Integer> entry : saleServiceDTO.getInventoryList().entrySet()) {
            Inventory inventory = inventoryRepo.findById(entry.getKey()).get();
            SaleInventory saleInventory = new SaleInventory();
            saleInventory.setSaleService(saleService);
            saleInventory.setInventory(inventory);
            saleInventory.setQty(entry.getValue());
            saleInventory.setPrize(entry.getValue() * inventory.getSaleUnitPrice());
            saleInventoryRepo.save(saleInventory);
        }

        saleServiceRepo.save(saleService);
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

//    @Override
//    public List<String> GetSupplierCode() {
//        return null;
//    }
}
