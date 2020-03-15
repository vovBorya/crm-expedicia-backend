package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.ManagerDto;

import java.util.List;

public interface ManagerService {

    List<ManagerDto> list();

    ManagerDto create(ManagerDto managerDto);

    ManagerDto get(Integer id);

    ManagerDto update(Integer id, ManagerDto managerDto);

    ManagerDto delete(Integer id);
}
