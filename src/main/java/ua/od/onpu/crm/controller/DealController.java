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
import ua.od.onpu.crm.dto.DealDto;
import ua.od.onpu.crm.service.DealService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/deals")
public class DealController {

    private DealService dealService;

    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<DealDto> list() {
        List<DealDto> response = dealService.list();
        log.info("GET deals: {}", response);
        return response;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public DealDto create(@RequestBody DealDto request) {
        DealDto response = dealService.create(request);
        log.info("CREATE deal {}", response);
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public DealDto get(@PathVariable Integer id) {
        DealDto response = dealService.get(id);
        log.info("GET deal by id = {}: {}", id, response);
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public DealDto update(@PathVariable Integer id, @RequestBody DealDto request) {
        DealDto response = dealService.update(id, request);
        log.info("UPDATE deal by id = {} : {}", id, response);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public DealDto delete(@PathVariable Integer id) {
        DealDto response = dealService.delete(id);
        log.info("DELETE deal by id = {}: {}", id, response);
        return response;
    }

    @GetMapping(params = "childId")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<DealDto> getDealsByChild(@RequestParam Integer childId) {
        List<DealDto> response = dealService.getDealsByChild(childId);
        log.info("GET deal by childId = {}: {}", childId, response);
        return response;
    }

    @GetMapping(params = "customerId")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<DealDto> getDealsByCustomer(@RequestParam Integer customerId) {
        List<DealDto> response = dealService.getDealsByCustomer(customerId);
        log.info("GET deal by customerId = {}: {}", customerId, response);
        return response;
    }

    @GetMapping(params = "employeeId")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<DealDto> getDealsByEmployee(@RequestParam Integer employeeId) {
        List<DealDto> response = dealService.getDealsByEmployee(employeeId);
        log.info("GET deal by employeeId = {}: {}", employeeId, response);
        return response;
    }

    @GetMapping(params = "expeditionId")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<DealDto> getDealsByExpedition(@RequestParam Integer expeditionId) {
        List<DealDto> response = dealService.getDealsByExpedition(expeditionId);
        log.info("GET deal by expeditionId = {}: {}", expeditionId, response);
        return response;
    }

    @GetMapping(params = {"status", "expeditionId", "sumBetween", "employeeId", "customerId", "childId", "sleepingBag"})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<DealDto> getDealByFilters(@RequestParam String status,
                                          @RequestParam Integer expeditionId,
                                          @RequestParam(defaultValue = "0, 2147483647") String[] sumBetween,
                                          @RequestParam Integer employeeId,
                                          @RequestParam Integer customerId,
                                          @RequestParam Integer childId,
                                          @RequestParam String sleepingBag) {
        List<DealDto> response = dealService.getFilteredDeal(status, expeditionId, Integer.parseInt(sumBetween[0]),
                Integer.parseInt(sumBetween[1]), employeeId, customerId, childId, sleepingBag);
        log.info("GET deal by filter = status - {}, expeditionId - {}, sumBetween - {}, " +
                        "employeeId - {}, customerId - {}, childId - {}, sleepingBag - {}: {}",
                status, expeditionId, sumBetween, employeeId, customerId, childId, sleepingBag, response);
        return response;
    }
}
