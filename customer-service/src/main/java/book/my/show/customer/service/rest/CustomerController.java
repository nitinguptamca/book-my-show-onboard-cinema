package book.my.show.customer.service.rest;


import book.my.show.customer.service.Entity.Customers;
import book.my.show.customer.service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customers")
@RestController
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<Customers> create(@Valid @RequestBody Customers customers){
        log.debug("Request body ::{}",customers);
        Customers customers1=customerService.create(customers);
        return new ResponseEntity<>(customers1, HttpStatus.CREATED);
    }
}
