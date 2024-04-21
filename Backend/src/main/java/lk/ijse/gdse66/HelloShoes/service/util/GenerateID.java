package lk.ijse.gdse66.HelloShoes.service.util;

import lk.ijse.gdse66.HelloShoes.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateID {
    @Autowired
    SupplierRepo supplierRepo;

    public  String generateSupplerCode() {

        try {
            String last_id =  supplierRepo.findFirstByOrderBySupplierCodeDesc().getSupplierCode();
            int latest_id = Integer.parseInt(last_id.split("SP00-")[1])+1;
            return "SP00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "SP00-001";
        }

    }

//    public  String generateItemCode() {
//
//        try {
//            String last_id =  itemRepo.findFirstByOrderByItemCodeDesc().getItemCode();
//            int latest_id = Integer.parseInt(last_id.split("I00-")[1])+1;
//            return "I00-"+String.format("%03d",latest_id);
//
//        } catch (NullPointerException e) {
//            return "I00-001";
//        }
//    }

//    public  String generateOrderID() {

       /* try {
            String last_id = String.valueOf(orderRepo.findLastOrder().getOrderId());
            int latest_id = Integer.parseInt(last_id.split("OR00-")[1])+1;
            return "OR00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "OR00-001";
        }*/
//        return null;

//    }

}
