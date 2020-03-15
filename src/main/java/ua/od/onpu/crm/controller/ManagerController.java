package ua.od.onpu.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.od.onpu.crm.dto.ManagerDto;
import ua.od.onpu.crm.service.ManagerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/managers")
public class ManagerController {

    private ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<ManagerDto> list() {
        List<ManagerDto> response = managerService.list();
        log.info("GET managers: {}", response);
        return response;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public ManagerDto create(@Valid @RequestBody ManagerDto request) {
        ManagerDto response = managerService.create(request);
        log.info("CREATE manager {}", response);
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public ManagerDto get(@PathVariable Integer id) {
        ManagerDto response = managerService.get(id);
        log.info("GET manager by id = {}: {}", id, response);
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ManagerDto update(@PathVariable Integer id, @Valid @RequestBody ManagerDto request) {
        ManagerDto response = managerService.update(id, request);
        log.info("UPDATE manager by id = {} : {}", id, response);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ManagerDto delete(@PathVariable Integer id) {
        ManagerDto response = managerService.delete(id);
        log.info("DELETE manager by id = {}: {}", id, response);
        return response;
    }
}
