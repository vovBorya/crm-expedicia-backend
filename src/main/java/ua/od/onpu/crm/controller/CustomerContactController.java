package ua.od.onpu.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.od.onpu.crm.dto.CustomerContactDto;
import ua.od.onpu.crm.service.CustomerContactService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/contacts")
public class CustomerContactController {

    private CustomerContactService customerContactService;

    @Autowired
    public CustomerContactController(CustomerContactService customerContactService) {
        this.customerContactService = customerContactService;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public CustomerContactDto create(@RequestBody CustomerContactDto request) {
        CustomerContactDto response = customerContactService.create(request);
        log.info("CREATE customer contact {}", response);
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerContactDto get(@PathVariable Integer id) {
        CustomerContactDto response = customerContactService.get(id);
        log.info("GET customer contact by id = {}: {}", id, response);
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerContactDto update(@PathVariable Integer id, @RequestBody CustomerContactDto request) {
        CustomerContactDto response = customerContactService.update(id, request);
        log.info("UPDATE customer contact by id = {} : {}", id, response);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerContactDto delete(@PathVariable Integer id) {
        CustomerContactDto response = customerContactService.delete(id);
        log.info("DELETE customer contact by id = {}: {}", id, response);
        return response;
    }

    @GetMapping(params = "customerId")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<CustomerContactDto> getContactsByCustomer(@RequestParam Integer customerId) {
        List<CustomerContactDto> response = customerContactService.getContactsByCustomer(customerId);
        log.info("GET contacts by customerId = {}: {}", customerId, response);
        return response;
    }
}
