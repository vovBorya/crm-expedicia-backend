package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Customer;
import ua.od.onpu.crm.dao.repository.CustomerRepository;
import ua.od.onpu.crm.dto.CustomerDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> list() {
        List<CustomerDto> list = new ArrayList<>();
        customerRepository.findAll().forEach(i -> list.add(buildToDto(i)));
        return list;
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = customerRepository.save(buildToEntity(customerDto));
        return buildToDto(customer);
    }

    @Override
    public CustomerDto get(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> logCustomerNotFound(id));
        return buildToDto(customer);
    }

    @Override
    public CustomerDto update(Integer id, CustomerDto customerDto) {
        Customer customer = findCustomerById(id);

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPatronymic(customerDto.getPatronymic());
        customer.setEmail(customerDto.getEmail());
        Customer response = customerRepository.save(customer);
        return buildToDto(response);
    }

    @Override
    public CustomerDto delete(Integer id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
        return buildToDto(customer);
    }

    private Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> logCustomerNotFound(id));
    }

    public static ResourceNotFoundException logCustomerNotFound(Integer id) {
        log.error("Customer with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Customer with id = " + id + " NOT_FOUND");
    }

    private CustomerDto buildToDto(Customer customer) {
        return CustomerDto.builder()
                .lastName(customer.getLastName())
                .firstName(customer.getFirstName())
                .email(customer.getEmail())
                .patronymic(customer.getPatronymic())
                .id(customer.getId())
                .build();
    }

    private Customer buildToEntity(CustomerDto customerDto) {
        return Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .patronymic(customerDto.getPatronymic())
                .email(customerDto.getEmail())
                .build();
    }
}
