package ua.od.onpu.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.od.onpu.crm.dto.PaymentDto;
import ua.od.onpu.crm.service.PaymentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/managers")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<PaymentDto> list() {
        List<PaymentDto> response = paymentService.list();
        log.info("GET deals: {}", response);
        return response;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public PaymentDto create(@Valid @RequestBody PaymentDto request) {
        PaymentDto response = paymentService.create(request);
        log.info("CREATE deal {}", response);
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public PaymentDto get(@PathVariable Integer id) {
        PaymentDto response = paymentService.get(id);
        log.info("GET deal by id = {}: {}", id, response);
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public PaymentDto update(@PathVariable Integer id, @Valid @RequestBody PaymentDto request) {
        PaymentDto response = paymentService.update(id, request);
        log.info("UPDATE deal by id = {} : {}", id, response);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public PaymentDto delete(@PathVariable Integer id) {
        PaymentDto response = paymentService.delete(id);
        log.info("DELETE deal by id = {}: {}", id, response);
        return response;
    }
}
