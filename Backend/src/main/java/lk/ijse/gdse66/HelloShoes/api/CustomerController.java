package lk.ijse.gdse66.HelloShoes.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.HelloShoes.dto.CustomersDTO;
import lk.ijse.gdse66.HelloShoes.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value ="/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomersDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(value ="/getIDs")
    public List<String> getAllInventoryIDs(){
        return customerService.getCustomersCodes();
    }

    @GetMapping(path = "/nextID")
    public String getCustomerCode(){
        return customerService.getCustomerCode();
    }

    @GetMapping(value ="/getName/{id}")
    public String getCustomerName(@PathVariable("id") String id){
        return customerService.getNameByID(id);
    }

    @GetMapping(path = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomersDTO getCustomerByEmail(@PathVariable("code") String code){
        return customerService.getCustomersDetails(code);
    }

    @GetMapping(value ="/birthday",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomersDTO> getBirthdayCustomers(){
        return customerService.getCustomersWithBirthdaysToday();
    }

    @PatchMapping(value ="/birthday",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveBirthdayCustomers( @Valid @RequestBody CustomersDTO customerDTO){
         customerService.saveBirthdayWishDate(customerDTO);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomersDTO saveCustomer( @Valid @RequestBody CustomersDTO customerDTO){
        return customerService.saveCustomers(customerDTO);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@Valid @RequestBody CustomersDTO customerDTO){
        customerService.updateCustomers(customerDTO);
    }

    @DeleteMapping(path = "/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("code") String code){
        customerService.deleteCustomers(code);
    }


}
