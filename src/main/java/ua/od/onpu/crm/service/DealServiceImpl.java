package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ua.od.onpu.crm.dao.model.Child;
import ua.od.onpu.crm.dao.model.Customer;
import ua.od.onpu.crm.dao.model.Deal;
import ua.od.onpu.crm.dao.model.Employee;
import ua.od.onpu.crm.dao.model.Expedition;
import ua.od.onpu.crm.dao.model.enums.DealStatus;
import ua.od.onpu.crm.dao.repository.ChildRepository;
import ua.od.onpu.crm.dao.repository.CustomerRepository;
import ua.od.onpu.crm.dao.repository.DealRepository;
import ua.od.onpu.crm.dao.repository.EmployeeRepository;
import ua.od.onpu.crm.dao.repository.ExpeditionRepository;
import ua.od.onpu.crm.dto.DealDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ua.od.onpu.crm.service.ChildServiceImpl.logChildNotFound;
import static ua.od.onpu.crm.service.CustomerServiceImpl.logCustomerNotFound;
import static ua.od.onpu.crm.service.EmployeeServiceImpl.logEmployeeNotFound;
import static ua.od.onpu.crm.service.ExpeditionServiceImpl.logExpeditionNotFound;

@Slf4j
@Service
public class DealServiceImpl implements DealService {

    private DealRepository dealRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private ChildRepository childRepository;
    private ExpeditionRepository expeditionRepository;

    @Autowired
    public DealServiceImpl(DealRepository dealRepository, CustomerRepository customerRepository,
                           EmployeeRepository employeeRepository, ChildRepository childRepository,
                           ExpeditionRepository expeditionRepository) {
        this.dealRepository = dealRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.childRepository = childRepository;
        this.expeditionRepository = expeditionRepository;
    }

    @Override
    public List<DealDto> list() {
        List<DealDto> list = new LinkedList<>();
        dealRepository.findAll().forEach(deal -> list.add(buildToDto(deal)));
        return list;
    }

    @Override
    public DealDto create(DealDto dealDto) {
        Deal deal = dealRepository.save(buildToEntity(dealDto));
        return buildToDto(deal);
    }

    @Override
    public DealDto get(Integer id) {
        Deal deal = findDealById(id);
        return buildToDto(deal);
    }

    @Override
    public DealDto update(Integer id, DealDto dto) {
        Deal deal = findDealById(id);

        Employee employee = findEmployeeById(dto.getEmployeeId());
        Customer customer = findCustomerById(dto.getCustomerId());
        Child child = findChildById(dto.getChildId());
        Expedition expedition = findExpeditionById(dto.getExpeditionId());

        deal.setSum(dto.getSum());
        deal.setStatus(dto.getStatus());
        deal.setDiscount(dto.getDiscount());
        deal.setDeparturePlace(dto.getDeparturePlace());
        deal.setTransportationWay(dto.getTransportationWay());
        deal.setComment(dto.getComment());
        deal.setEmployee(employee);
        deal.setCustomer(customer);
        deal.setChild(child);
        deal.setExpedition(expedition);
        deal.setSleepingBag(dto.getSleepingBag());

        Deal response = dealRepository.save(deal);
        return buildToDto(response);
    }

    @Override
    public DealDto delete(Integer id) {
        Deal deal = findDealById(id);
        dealRepository.delete(deal);
        return buildToDto(deal);
    }

    @Override
    public List<DealDto> getDealsByChild(Integer childId) {
        return dealRepository.findAllByChildId(childId)
                .stream()
                .map(this::buildToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DealDto> getDealsByCustomer(Integer customerId) {
        return dealRepository.findAllByCustomerId(customerId)
                .stream()
                .map(this::buildToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DealDto> getDealsByEmployee(Integer employeeId) {
        return dealRepository.findAllByEmployeeId(employeeId)
                .stream()
                .map(this::buildToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DealDto> getDealsByExpedition(Integer expeditionId) {
        return dealRepository.findAllByExpeditionId(expeditionId)
                .stream()
                .map(this::buildToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DealDto> getFilteredDeal(String status, Integer expeditionId, Integer startSum, Integer endSum,
                                         Integer employeeId, Integer customerId, Integer childId, String sleepingBag) {
        return dealRepository.getFilteredDeal(status, expeditionId, startSum, endSum,
                employeeId, customerId, childId, sleepingBag)
                .stream()
                .map(this::buildToDto)
                .collect(Collectors.toList());
    }

    static ResourceNotFoundException logDealNotFound(Integer id) {
        log.error("Deal with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Deal with id = " + id + " NOT_FOUND");
    }

    private Deal findDealById(Integer id) {
        return dealRepository.findById(id)
                .orElseThrow(() -> logDealNotFound(id));
    }

    private Employee findEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> logEmployeeNotFound(id));
    }

    private Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> logCustomerNotFound(id));
    }

    private Child findChildById(Integer id) {
        return childRepository.findById(id)
                .orElseThrow(() -> logChildNotFound(id));
    }

    private Expedition findExpeditionById(Integer id) {
        return expeditionRepository.findById(id)
                .orElseThrow(() -> logExpeditionNotFound(id));
    }

    private DealDto buildToDto(Deal deal) {
        return DealDto.builder()
                .id(deal.getId())
                .sum(deal.getSum())
                .status(deal.getStatus())
                .discount(deal.getDiscount())
                .departurePlace(deal.getDeparturePlace())
                .transportationWay(deal.getTransportationWay())
                .comment(deal.getComment())
                .employeeId(deal.getEmployee().getId())
                .customerId(deal.getCustomer().getId())
                .childId(deal.getChild().getId())
                .expeditionId(deal.getExpedition().getId())
                .sleepingBag(deal.getSleepingBag())
                .build();
    }

    private Deal buildToEntity(DealDto dto) {
        Employee employee = findEmployeeById(dto.getEmployeeId());
        Customer customer = findCustomerById(dto.getCustomerId());
        Child child = findChildById(dto.getChildId());
        Expedition expedition = findExpeditionById(dto.getExpeditionId());

        return Deal.builder()
                .sum(dto.getSum())
                .status(dto.getStatus())
                .discount(dto.getDiscount())
                .departurePlace(dto.getDeparturePlace())
                .transportationWay(dto.getTransportationWay())
                .comment(dto.getComment())
                .employee(employee)
                .customer(customer)
                .child(child)
                .expedition(expedition)
                .sleepingBag(dto.getSleepingBag())
                .build();
    }
}
