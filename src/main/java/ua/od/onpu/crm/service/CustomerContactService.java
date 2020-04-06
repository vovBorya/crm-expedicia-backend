package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.CustomerContactDto;

import java.util.List;

public interface CustomerContactService {

    CustomerContactDto create(CustomerContactDto customerContactDto);

    CustomerContactDto get(Integer id);

    CustomerContactDto update(Integer id, CustomerContactDto customerContactDto);

    CustomerContactDto delete(Integer id);

    List<CustomerContactDto> getContactsByCustomer(Integer customerId);
}
