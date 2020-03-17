package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Manager;
import ua.od.onpu.crm.dao.repository.ManagerRepository;
import ua.od.onpu.crm.dto.ManagerDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<ManagerDto> list() {
        List<ManagerDto> list = new LinkedList<>();
        managerRepository.findAll().forEach(manager -> list.add(buildToDto(manager)));
        return list;
    }

    @Override
    public ManagerDto create(ManagerDto managerDto) {
        Manager manager = managerRepository.save(buildToEntity(managerDto));
        return buildToDto(manager);
    }

    @Override
    public ManagerDto get(Integer id) {
        Manager manager = findManagerById(id);
        return buildToDto(manager);
    }

    @Override
    public ManagerDto update(Integer id, ManagerDto managerDto) {
        Manager manager = findManagerById(id);

        manager.setBirthday(managerDto.getBirthday());
        manager.setEmail(managerDto.getEmail());
        manager.setFirstName(managerDto.getFirstName());
        manager.setLastName(managerDto.getLastName());
        manager.setPatronymic(managerDto.getPatronymic());
        manager.setPhone(managerDto.getPhone());
        manager.setSalary(managerDto.getSalary());

        Manager response = managerRepository.save(manager);
        return buildToDto(response);
    }

    @Override
    public ManagerDto delete(Integer id) {
        Manager manager = findManagerById(id);
        managerRepository.delete(manager);
        return buildToDto(manager);
    }

    static ResourceNotFoundException logManagerNotFound(Integer id) {
        log.error("Manager with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Manager with id = " + id + " NOT_FOUND");
    }

    private Manager findManagerById(Integer id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> logManagerNotFound(id));
    }

    private ManagerDto buildToDto(Manager manager) {
        return ManagerDto.builder()
                .id(manager.getId())
                .birthday(manager.getBirthday())
                .email(manager.getEmail())
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .patronymic(manager.getPatronymic())
                .phone(manager.getPhone())
                .salary(manager.getSalary())
                .build();
    }

    private Manager buildToEntity(ManagerDto dto) {
        return Manager.builder()
                .birthday(dto.getBirthday())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymic(dto.getPatronymic())
                .phone(dto.getPhone())
                .salary(dto.getSalary())
                .build();
    }
}
