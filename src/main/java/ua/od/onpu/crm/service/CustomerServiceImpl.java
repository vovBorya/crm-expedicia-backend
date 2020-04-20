package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Customer;
import ua.od.onpu.crm.dao.repository.CustomerRepository;
import ua.od.onpu.crm.dto.CustomerDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;
import ua.od.onpu.crm.service.provider.NameProvider;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private NameProvider nameProvider;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, NameProvider nameProvider) {
        this.customerRepository = customerRepository;
        this.nameProvider = nameProvider;
    }

    @Override
    public List<CustomerDto> list() {
        List<CustomerDto> list = new LinkedList<>();
        customerRepository.findAll().forEach(customer -> list.add(buildToDto(customer)));
        return list;
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = customerRepository.save(buildToEntity(customerDto));
        return buildToDto(customer);
    }

    @Override
    public CustomerDto get(Integer id) {
        Customer customer = findCustomerById(id);
        return buildToDto(customer);
    }

    @Override
    public CustomerDto update(Integer id, CustomerDto customerDto) {
        Customer customer = findCustomerById(id);

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPatronymic(customerDto.getPatronymic());
        customer.setCity(customerDto.getCity());
        customer.setStatus(customerDto.getStatus());
        customer.setExemptions(customerDto.getExemptions());
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

    static ResourceNotFoundException logCustomerNotFound(Integer id) {
        log.error("Customer with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Customer with id = " + id + " NOT_FOUND");
    }

    private CustomerDto buildToDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .lastName(customer.getLastName())
                .firstName(customer.getFirstName())
                .patronymic(customer.getPatronymic())
                .fullName(nameProvider.getFullName(customer.getLastName(), customer.getFirstName(),
                        customer.getPatronymic()))
                .city(customer.getCity())
                .status(customer.getStatus())
                .exemptions(customer.getExemptions())
                .build();
    }

    private Customer buildToEntity(CustomerDto customerDto) {
        return Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .patronymic(customerDto.getPatronymic())
                .city(customerDto.getCity())
                .status(customerDto.getStatus())
                .exemptions(customerDto.getExemptions())
                .build();
    }
}
