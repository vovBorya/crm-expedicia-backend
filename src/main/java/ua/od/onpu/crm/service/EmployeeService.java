package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> list();

    EmployeeDto create(EmployeeDto employeeDto);

    EmployeeDto get(Integer id);

    EmployeeDto update(Integer id, EmployeeDto employeeDto);

    EmployeeDto delete(Integer id);
}
