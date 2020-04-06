package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Employee;
import ua.od.onpu.crm.dao.repository.EmployeeRepository;
import ua.od.onpu.crm.dto.EmployeeDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;
import ua.od.onpu.crm.service.provider.NameProvider;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private NameProvider nameProvider;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, NameProvider nameProvider) {
        this.employeeRepository = employeeRepository;
        this.nameProvider = nameProvider;
    }

    @Override
    public List<EmployeeDto> list() {
        List<EmployeeDto> list = new LinkedList<>();
        employeeRepository.findAll().forEach(employee -> list.add(buildToDto(employee)));
        return list;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.save(buildToEntity(employeeDto));
        return buildToDto(employee);
    }

    @Override
    public EmployeeDto get(Integer id) {
        Employee employee = findEmployeeById(id);
        return buildToDto(employee);
    }

    @Override
    public EmployeeDto update(Integer id, EmployeeDto employeeDto) {
        Employee employee = findEmployeeById(id);

        employee.setBirthday(employeeDto.getBirthday());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPatronymic(employeeDto.getPatronymic());
        employee.setPhone(employeeDto.getPhone());
        employee.setSalary(employeeDto.getSalary());

        Employee response = employeeRepository.save(employee);
        return buildToDto(response);
    }

    @Override
    public EmployeeDto delete(Integer id) {
        Employee employee = findEmployeeById(id);
        employeeRepository.delete(employee);
        return buildToDto(employee);
    }

    static ResourceNotFoundException logEmployeeNotFound(Integer id) {
        log.error("Employee with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Employee with id = " + id + " NOT_FOUND");
    }

    private Employee findEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> logEmployeeNotFound(id));
    }

    private EmployeeDto buildToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .birthday(employee.getBirthday())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .patronymic(employee.getPatronymic())
                .fullName(nameProvider.getFullName(employee.getLastName(), employee.getFirstName(),
                        employee.getPatronymic()))
                .phone(employee.getPhone())
                .salary(employee.getSalary())
                .build();
    }

    private Employee buildToEntity(EmployeeDto dto) {
        return Employee.builder()
                .birthday(dto.getBirthday())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .phone(dto.getPhone())
                .salary(dto.getSalary())
                .build();
    }
}
