package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.ChildDto;

import java.util.List;

public interface ChildService {

    List<ChildDto> list();

    ChildDto create(ChildDto childDto);

    ChildDto get(Integer id);

    ChildDto update(Integer id, ChildDto childDt);

    ChildDto delete(Integer id);

    List<ChildDto> getChildrenByParent(Integer parentId);

    List<ChildDto> getFilteredChildren(String fullName, Integer startAge, Integer endAge, Integer parentId);
}
