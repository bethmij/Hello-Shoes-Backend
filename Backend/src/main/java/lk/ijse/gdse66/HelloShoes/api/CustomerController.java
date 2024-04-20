package lk.ijse.gdse66.HelloShoes.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerse")
public class CustomerController {

    @GetMapping
    public String getMapping(){
        System.out.println("Msg Received");
        return "Msg sent";
    }
}
