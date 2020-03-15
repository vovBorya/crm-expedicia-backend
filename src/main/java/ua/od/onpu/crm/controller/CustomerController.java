package ua.od.onpu.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.od.onpu.crm.dto.CustomerDto;
import ua.od.onpu.crm.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerDto> list() {
        List<CustomerDto> response = customerService.list();
        log.info("GET customer: {}", response);
        return response;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public CustomerDto create(@Valid @RequestBody CustomerDto request) {
        CustomerDto response = customerService.create(request);
        log.info("CREATE customer {}", response);
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerDto get(@PathVariable Integer id) {
        CustomerDto response = customerService.get(id);
        log.info("GET customer by id = {}: {}", id, response);
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerDto update(@PathVariable Integer id, @Valid @RequestBody CustomerDto request) {
        CustomerDto response = customerService.update(id, request);
        log.info("UPDATE customer by id = {} : {}", id, response);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerDto delete(@PathVariable Integer id) {
        CustomerDto response = customerService.delete(id);
        log.info("DELETE customer by id = {}: {}", id, response);
        return response;
    }
}
