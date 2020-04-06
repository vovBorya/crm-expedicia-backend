package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Child;
import ua.od.onpu.crm.dao.model.Customer;
import ua.od.onpu.crm.dao.repository.ChildRepository;
import ua.od.onpu.crm.dao.repository.CustomerRepository;
import ua.od.onpu.crm.dto.ChildDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;
import ua.od.onpu.crm.service.provider.NameProvider;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ua.od.onpu.crm.service.CustomerServiceImpl.logCustomerNotFound;


@Service
@Slf4j
public class ChildServiceImpl implements ChildService {

    private ChildRepository childRepository;
    private CustomerRepository customerRepository;
    private NameProvider nameProvider;

    @Autowired
    public ChildServiceImpl(ChildRepository childRepository, CustomerRepository customerRepository,
                            NameProvider nameProvider) {
        this.childRepository = childRepository;
        this.customerRepository = customerRepository;
        this.nameProvider = nameProvider;
    }

    @Override
    public List<ChildDto> list() {
        List<ChildDto> list = new LinkedList<>();
        childRepository.findAll().forEach(i -> list.add(buildToDto(i)));
        return list;
    }

    @Override
    public ChildDto create(ChildDto childDto) {
        Child child = childRepository.save(buildToEntity(childDto));
        return buildToDto(child);
    }

    @Override
    public ChildDto get(Integer id) {
        Child child = findChildById(id);
        return buildToDto(child);
    }

    @Override
    public ChildDto update(Integer id, ChildDto childDto) {
        Customer parent = findCustomerById(childDto.getParentId());
        Child child = findChildById(id);

        child.setLastName(childDto.getLastName());
        child.setFirstName(childDto.getFirstName());
        child.setPatronymic(childDto.getPatronymic());
        child.setBirthday(childDto.getBirthday());
        child.setParent(parent);
        Child response = childRepository.save(child);
        return buildToDto(response);
    }

    @Override
    public ChildDto delete(Integer id) {
        Child child = findChildById(id);
        childRepository.delete(child);
        return buildToDto(child);
    }

    @Override
    public List<ChildDto> getChildrenByParent(Integer parentId) {
        return findCustomerById(parentId).getChildren()
                .stream()
                .map(this::buildToDto)
                .collect(Collectors.toList());
    }

    private Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> logCustomerNotFound(id));
    }

    private Child findChildById(Integer id) {
        return childRepository.findById(id)
                .orElseThrow(() -> logChildNotFound(id));
    }

    static ResourceNotFoundException logChildNotFound(Integer id) {
        log.error("Child with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Child with id = " + id + " NOT_FOUND");
    }

    private ChildDto buildToDto(Child child) {
        return ChildDto.builder()
                .id(child.getId())
                .firstName(child.getFirstName())
                .lastName(child.getLastName())
                .patronymic(child.getPatronymic())
                .fullName(nameProvider.getFullName(child.getLastName(), child.getFirstName(), child.getPatronymic()))
                .birthday(child.getBirthday())
                .parentId(child.getParent() != null ? child.getParent().getId() : null)
                .build();
    }

    private Child buildToEntity(ChildDto dto) {
        Child child = Child.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .birthday(dto.getBirthday())
                .build();
        if (dto.getParentId() != null) {
            child.setParent(findCustomerById(dto.getParentId()));
        }
        return child;
    }
}
