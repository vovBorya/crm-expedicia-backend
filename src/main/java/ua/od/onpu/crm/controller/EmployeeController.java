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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.od.onpu.crm.dto.EmployeeDto;
import ua.od.onpu.crm.service.EmployeeService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<EmployeeDto> list() {
        List<EmployeeDto> response = employeeService.list();
        log.info("GET employees: {}", response);
        return response;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public EmployeeDto create(@RequestBody EmployeeDto request) {
        EmployeeDto response = employeeService.create(request);
        log.info("CREATE employee {}", response);
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeDto get(@PathVariable Integer id) {
        EmployeeDto response = employeeService.get(id);
        log.info("GET employee by id = {}: {}", id, response);
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public EmployeeDto update(@PathVariable Integer id, @RequestBody EmployeeDto request) {
        EmployeeDto response = employeeService.update(id, request);
        log.info("UPDATE employee by id = {} : {}", id, response);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public EmployeeDto delete(@PathVariable Integer id) {
        EmployeeDto response = employeeService.delete(id);
        log.info("DELETE employee by id = {}: {}", id, response);
        return response;
    }
}
