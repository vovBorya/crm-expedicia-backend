package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> list();

    CustomerDto create(CustomerDto customerDto);

    CustomerDto get(Integer id);

    CustomerDto update(Integer id, CustomerDto customerDto);

    CustomerDto delete(Integer id);

}
