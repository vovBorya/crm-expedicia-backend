package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Customer;
import ua.od.onpu.crm.dao.model.CustomerContact;
import ua.od.onpu.crm.dao.repository.CustomerContactRepository;
import ua.od.onpu.crm.dao.repository.CustomerRepository;
import ua.od.onpu.crm.dto.CustomerContactDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static ua.od.onpu.crm.service.CustomerServiceImpl.logCustomerNotFound;

@Slf4j
@Service
public class CustomerContactServiceImpl implements CustomerContactService {

    private CustomerRepository customerRepository;
    private CustomerContactRepository customerContactRepository;

    @Autowired
    public CustomerContactServiceImpl(CustomerRepository customerRepository, CustomerContactRepository customerContactRepository) {
        this.customerRepository = customerRepository;
        this.customerContactRepository = customerContactRepository;
    }

    @Override
    public CustomerContactDto create(CustomerContactDto customerContactDto) {
        CustomerContact customerContact = customerContactRepository.save(buildToEntity(customerContactDto));
        return buildToDto(customerContact);
    }

    @Override
    public CustomerContactDto get(Integer id) {
        CustomerContact customerContact = findCustomerContactById(id);
        return buildToDto(customerContact);
    }

    @Override
    public CustomerContactDto update(Integer id, CustomerContactDto customerContactDto) {
        Customer customer = findCustomerById(customerContactDto.getCustomerId());
        CustomerContact customerContact = findCustomerContactById(id);

        customerContact.setType(customerContactDto.getType());
        customerContact.setContent(customerContactDto.getContent());
        customerContact.setCustomer(customer);

        CustomerContact response = customerContactRepository.save(customerContact);
        return buildToDto(response);
    }

    @Override
    public CustomerContactDto delete(Integer id) {
        CustomerContact customerContact = findCustomerContactById(id);
        customerContactRepository.delete(customerContact);
        return buildToDto(customerContact);
    }

    @Override
    public List<CustomerContactDto> getContactsByCustomer(Integer customerId) {
        return customerContactRepository.findAllByCustomerId(customerId)
                .stream()
                .map(this::buildToDto)
                .collect(Collectors.toList());
    }

    static ResourceNotFoundException logCustomerContactNotFound(Integer id) {
        log.error("CustomerContact with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("CustomerContact with id = " + id + " NOT_FOUND");
    }

    private CustomerContact findCustomerContactById(Integer id) {
        return customerContactRepository.findById(id)
                .orElseThrow(() -> logCustomerContactNotFound(id));
    }

    private Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> logCustomerNotFound(id));
    }

    private CustomerContactDto buildToDto(CustomerContact customerContact) {
        return CustomerContactDto.builder()
                .id(customerContact.getId())
                .type(customerContact.getType())
                .content(customerContact.getContent())
                .customerId(customerContact.getCustomer().getId())
                .build();
    }

    private CustomerContact buildToEntity(CustomerContactDto dto) {
        Customer customer = findCustomerById(dto.getCustomerId());

        return CustomerContact.builder()
                .customer(customer)
                .type(dto.getType())
                .content(dto.getContent())
                .build();
    }
}
