package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.CustomerContactDto;

public interface CustomerContactService {

    CustomerContactDto create(CustomerContactDto customerContactDto);

    CustomerContactDto get(Integer id);

    CustomerContactDto update(Integer id, CustomerContactDto customerContactDto);

    CustomerContactDto delete(Integer id);
}
