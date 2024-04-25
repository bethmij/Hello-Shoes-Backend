package lk.ijse.gdse66.HelloShoes.service.util;

import lk.ijse.gdse66.HelloShoes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateID {

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    SaleServiceRepo saleServiceRepo;

    public  String generateSupplerCode() {

        try {
            String last_id =  supplierRepo.findFirstByOrderBySupplierCodeDesc().getSupplierCode();
            int latest_id = Integer.parseInt(last_id.split("SP00-")[1])+1;
            return "SP00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "SP00-001";
        }
    }

    public  String generateEmployeeCode() {

        try {
            String last_id =  employeeRepo.findFirstByOrderByEmployeeCodeDesc().getEmployeeCode();
            int latest_id = Integer.parseInt(last_id.split("EP00-")[1])+1;
            return "EP00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "EP00-001";
        }
    }

    public  String generateCustomerCode() {

        try {
            String last_id =  customerRepo.findFirstByOrderByCustomerCodeDesc().getCustomerCode();
            int latest_id = Integer.parseInt(last_id.split("C00-")[1])+1;
            return "C00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "C00-001";
        }
    }

    public  String generateItemCode() {

        try {
            String last_id =  inventoryRepo.findFirstByOrderByItemCodeDesc().getItemCode();
            int latest_id = Integer.parseInt(last_id.split("I00-")[1])+1;
            return "I00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "I00-001";
        }
    }

    public  String generateSaleCode() {

        try {
            String last_id =  saleServiceRepo.findFirstByOrderByOrderNoDesc().getOrderNo();
            int latest_id = Integer.parseInt(last_id.split("OR00-")[1])+1;
            return "OR00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "OR00-001";
        }
    }



}
