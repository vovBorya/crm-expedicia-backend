package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.ExpeditionDto;

import java.util.List;

public interface ExpeditionService {

    List<ExpeditionDto> list();

    ExpeditionDto create(ExpeditionDto expeditionDto);

    ExpeditionDto get(Integer id);

    ExpeditionDto update(Integer id, ExpeditionDto expeditionDto);

    ExpeditionDto delete(Integer id);
}
