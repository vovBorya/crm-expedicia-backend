package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.DealDto;

import java.util.List;

public interface DealService {

    List<DealDto> list();

    DealDto create(DealDto dealDto);

    DealDto get(Integer id);

    DealDto update(Integer id, DealDto dealDto);

    DealDto delete(Integer id);
}
