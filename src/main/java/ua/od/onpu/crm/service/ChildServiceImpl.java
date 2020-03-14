package ua.od.onpu.crm.service;

import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Child;
import ua.od.onpu.crm.dao.repository.ChildRepository;
import ua.od.onpu.crm.dto.ChildDto;

@Service
public class ChildServiceImpl implements ChildService {

    private ChildRepository repository;

    public ChildServiceImpl(ChildRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChildDto create(ChildDto childDto) {

        repository.save(buildToEntity(childDto));
        return null;
    }

    @Override
    public ChildDto get(Integer id) {
        return null;
    }

    @Override
    public ChildDto update(Integer id, ChildDto childDt) {
        return null;
    }

    @Override
    public ChildDto delete(Integer id) {
        return null;
    }

    private ChildDto buildToDto(Child child) {
        return null;
    }

    private Child buildToEntity(ChildDto dto) {
        Child child = new Child();


        return child;
    }

}
