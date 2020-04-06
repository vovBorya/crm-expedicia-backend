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
import ua.od.onpu.crm.dto.PaymentDto;
import ua.od.onpu.crm.service.PaymentService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/payments")
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
    public PaymentDto create(@RequestBody PaymentDto request) {
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
    public PaymentDto update(@PathVariable Integer id, @RequestBody PaymentDto request) {
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

    @GetMapping(params = "dealId")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<PaymentDto> getPaymentsByDeal(@RequestParam Integer dealId) {
        List<PaymentDto> response = paymentService.getPaymentsByDeal(dealId);
        log.info("GET payments by dealId = {}: {}", dealId, response);
        return response;
    }
}
