package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Customer;
import ua.od.onpu.crm.dao.model.CustomerPhone;
import ua.od.onpu.crm.dao.repository.CustomerPhoneRepository;
import ua.od.onpu.crm.dao.repository.CustomerRepository;
import ua.od.onpu.crm.dto.CustomerPhoneDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;

import static ua.od.onpu.crm.service.CustomerServiceImpl.logCustomerNotFound;

@Slf4j
@Service
public class CustomerPhoneServiceImpl implements CustomerPhoneService {

    private CustomerRepository customerRepository;
    private CustomerPhoneRepository customerPhoneRepository;

    @Autowired
    public CustomerPhoneServiceImpl(CustomerRepository customerRepository, CustomerPhoneRepository customerPhoneRepository) {
        this.customerRepository = customerRepository;
        this.customerPhoneRepository = customerPhoneRepository;
    }

    @Override
    public CustomerPhoneDto create(CustomerPhoneDto customerPhoneDto) {
        CustomerPhone customerPhone = customerPhoneRepository.save(buildToEntity(customerPhoneDto));
        return buildToDto(customerPhone);
    }

    @Override
    public CustomerPhoneDto get(Integer id) {
        CustomerPhone customerPhone = findCustomerPhoneById(id);
        return buildToDto(customerPhone);
    }

    @Override
    public CustomerPhoneDto update(Integer id, CustomerPhoneDto customerPhoneDto) {
        Customer customer = findCustomerById(customerPhoneDto.getCustomerId());
        CustomerPhone customerPhone = findCustomerPhoneById(id);

        customerPhone.setPhone(customerPhoneDto.getPhone());
        customerPhone.setCustomer(customer);

        CustomerPhone response = customerPhoneRepository.save(customerPhone);
        return buildToDto(response);
    }

    @Override
    public CustomerPhoneDto delete(Integer id) {
        CustomerPhone customerPhone = findCustomerPhoneById(id);
        customerPhoneRepository.delete(customerPhone);
        return buildToDto(customerPhone);
    }

    static ResourceNotFoundException logCustomerPhoneNotFound(Integer id) {
        log.error("CustomerPhone with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("CustomerPhone with id = " + id + " NOT_FOUND");
    }

    private CustomerPhone findCustomerPhoneById(Integer id) {
        return customerPhoneRepository.findById(id)
                .orElseThrow(() -> logCustomerPhoneNotFound(id));
    }

    private Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> logCustomerNotFound(id));
    }

    private CustomerPhoneDto buildToDto(CustomerPhone customerPhone) {
        return CustomerPhoneDto.builder()
                .id(customerPhone.getId())
                .phone(customerPhone.getPhone())
                .customerId(customerPhone.getCustomer().getId())
                .build();
    }

    private CustomerPhone buildToEntity(CustomerPhoneDto dto) {
        Customer customer = findCustomerById(dto.getCustomerId());

        return CustomerPhone.builder()
                .customer(customer)
                .phone(dto.getPhone())
                .build();
    }
}
