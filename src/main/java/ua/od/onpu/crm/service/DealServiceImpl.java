package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Child;
import ua.od.onpu.crm.dao.model.Customer;
import ua.od.onpu.crm.dao.model.Deal;
import ua.od.onpu.crm.dao.model.Expedition;
import ua.od.onpu.crm.dao.model.Manager;
import ua.od.onpu.crm.dao.repository.ChildRepository;
import ua.od.onpu.crm.dao.repository.CustomerRepository;
import ua.od.onpu.crm.dao.repository.DealRepository;
import ua.od.onpu.crm.dao.repository.ExpeditionRepository;
import ua.od.onpu.crm.dao.repository.ManagerRepository;
import ua.od.onpu.crm.dto.DealDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;

import java.util.LinkedList;
import java.util.List;

import static ua.od.onpu.crm.service.ChildServiceImpl.logChildNotFound;
import static ua.od.onpu.crm.service.CustomerServiceImpl.logCustomerNotFound;
import static ua.od.onpu.crm.service.ExpeditionServiceImpl.logExpeditionNotFound;
import static ua.od.onpu.crm.service.ManagerServiceImpl.logManagerNotFound;

@Slf4j
@Service
public class DealServiceImpl implements DealService {

    private DealRepository dealRepository;
    private CustomerRepository customerRepository;
    private ManagerRepository managerRepository;
    private ChildRepository childRepository;
    private ExpeditionRepository expeditionRepository;

    @Autowired
    public DealServiceImpl(DealRepository dealRepository, CustomerRepository customerRepository,
                           ManagerRepository managerRepository, ChildRepository childRepository,
                           ExpeditionRepository expeditionRepository) {
        this.dealRepository = dealRepository;
        this.customerRepository = customerRepository;
        this.managerRepository = managerRepository;
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

        Manager manager = findManagerById(dto.getManagerId());
        Customer customer = findCustomerById(dto.getCustomerId());
        Child child = findChildById(dto.getChildId());
        Expedition expedition = findExpeditionById(dto.getExpeditionId());

        deal.setSum(dto.getSum());
        deal.setStatus(dto.getStatus());
        deal.setComment(dto.getComment());
        deal.setManager(manager);
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

    static ResourceNotFoundException logDealNotFound(Integer id) {
        log.error("Deal with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Deal with id = " + id + " NOT_FOUND");
    }

    private Deal findDealById(Integer id) {
        return dealRepository.findById(id)
                .orElseThrow(() -> logDealNotFound(id));
    }

    private Manager findManagerById(Integer id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> logManagerNotFound(id));
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
                .comment(deal.getComment())
                .managerId(deal.getManager().getId())
                .customerId(deal.getCustomer().getId())
                .childId(deal.getChild().getId())
                .expeditionId(deal.getExpedition().getId())
                .sleepingBag(deal.getSleepingBag())
                .build();
    }

    private Deal buildToEntity(DealDto dto) {
        Manager manager = findManagerById(dto.getManagerId());
        Customer customer = findCustomerById(dto.getCustomerId());
        Child child = findChildById(dto.getChildId());
        Expedition expedition = findExpeditionById(dto.getExpeditionId());

        return Deal.builder()
                .sum(dto.getSum())
                .status(dto.getStatus())
                .comment(dto.getComment())
                .manager(manager)
                .customer(customer)
                .child(child)
                .expedition(expedition)
                .sleepingBag(dto.getSleepingBag())
                .build();
    }
}
