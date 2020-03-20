package ua.od.onpu.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.od.onpu.crm.dto.DealDto;
import ua.od.onpu.crm.service.DealService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
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
    public List<DealDto> list(HttpServletResponse httpResponse) {
        List<DealDto> response = dealService.list();
        httpResponse.addHeader("X-Total-Count", String.format("%s", response.size()));
        httpResponse.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
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
}
