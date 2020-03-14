package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.ChildDto;

public interface ChildService {

    ChildDto create(ChildDto childDto);

    ChildDto get(Integer id);

    ChildDto update(Integer id, ChildDto childDt);

    ChildDto delete(Integer id);

}
