package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.CustomerPhoneDto;

public interface CustomerPhoneService {

    CustomerPhoneDto create(CustomerPhoneDto customerPhoneDto);

    CustomerPhoneDto get(Integer id);

    CustomerPhoneDto update(Integer id, CustomerPhoneDto customerPhoneDto);

    CustomerPhoneDto delete(Integer id);
}
