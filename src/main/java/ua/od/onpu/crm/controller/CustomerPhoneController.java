package ua.od.onpu.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.od.onpu.crm.dto.CustomerPhoneDto;
import ua.od.onpu.crm.service.CustomerPhoneService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/phones")
public class CustomerPhoneController {

    private CustomerPhoneService customerPhoneService;

    @Autowired
    public CustomerPhoneController(CustomerPhoneService customerPhoneService) {
        this.customerPhoneService = customerPhoneService;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public CustomerPhoneDto create(@Valid @RequestBody CustomerPhoneDto request) {
        CustomerPhoneDto response = customerPhoneService.create(request);
        log.info("CREATE customer phone {}", response);
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerPhoneDto get(@PathVariable Integer id) {
        CustomerPhoneDto response = customerPhoneService.get(id);
        log.info("GET customer phone by id = {}: {}", id, response);
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerPhoneDto update(@PathVariable Integer id, @Valid @RequestBody CustomerPhoneDto request) {
        CustomerPhoneDto response = customerPhoneService.update(id, request);
        log.info("UPDATE customer phone by id = {} : {}", id, response);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerPhoneDto delete(@PathVariable Integer id) {
        CustomerPhoneDto response = customerPhoneService.delete(id);
        log.info("DELETE customer phone by id = {}: {}", id, response);
        return response;
    }
}
