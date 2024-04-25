package lk.ijse.gdse66.HelloShoes.service.util;

import lk.ijse.gdse66.HelloShoes.repository.EmployeeRepo;
import lk.ijse.gdse66.HelloShoes.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateID {

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    public  String generateSupplerCode() {

        try {
            String last_id =  supplierRepo.findFirstByOrderBySupplierCodeDesc().getSupplierCode();
            int latest_id = Integer.parseInt(last_id.split("SP00-")[1])+1;
            return "SP00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "SP00-001";
        }

    }

    public  String generateSEmployeeCode() {

        try {
            String last_id =  employeeRepo.findFirstByOrderByEmployeeCodeDesc().getEmployeeCode();
            int latest_id = Integer.parseInt(last_id.split("EP00-")[1])+1;
            return "EP00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "EP00-001";
        }

    }



}
