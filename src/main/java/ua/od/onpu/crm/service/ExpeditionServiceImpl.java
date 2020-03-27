package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Expedition;
import ua.od.onpu.crm.dao.repository.ExpeditionRepository;
import ua.od.onpu.crm.dto.ExpeditionDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class ExpeditionServiceImpl implements ExpeditionService {

    private ExpeditionRepository expeditionRepository;

    @Autowired
    public ExpeditionServiceImpl(ExpeditionRepository expeditionRepository) {
        this.expeditionRepository = expeditionRepository;
    }

    @Override
    public List<ExpeditionDto> list() {
        List<ExpeditionDto> list = new LinkedList<>();
        expeditionRepository.findAll().forEach(expedition -> list.add(buildToDto(expedition)));
        return list;
    }

    @Override
    public ExpeditionDto create(ExpeditionDto expeditionDto) {
        Expedition expedition = expeditionRepository.save(buildToEntity(expeditionDto));
        return buildToDto(expedition);
    }

    @Override
    public ExpeditionDto get(Integer id) {
        Expedition expedition = findExpeditionById(id);
        return buildToDto(expedition);
    }

    @Override
    public ExpeditionDto update(Integer id, ExpeditionDto expeditionDto) {
        Expedition expedition = findExpeditionById(id);

        expedition.setAbbreviation(expeditionDto.getAbbreviation());
        expedition.setLocation(expeditionDto.getLocation());

        Expedition response = expeditionRepository.save(expedition);
        return buildToDto(response);
    }

    @Override
    public ExpeditionDto delete(Integer id) {
        Expedition expedition = findExpeditionById(id);
        expeditionRepository.delete(expedition);
        return buildToDto(expedition);
    }

    static ResourceNotFoundException logExpeditionNotFound(Integer id) {
        log.error("Manager with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Manager with id = " + id + " NOT_FOUND");
    }

    private Expedition findExpeditionById(Integer id) {
        return expeditionRepository.findById(id)
                .orElseThrow(() -> logExpeditionNotFound(id));
    }

    private ExpeditionDto buildToDto(Expedition expedition) {
        return ExpeditionDto.builder()
                .id(expedition.getId())
                .abbreviation(expedition.getAbbreviation())
                .location(expedition.getLocation())
                .build();
    }

    private Expedition buildToEntity(ExpeditionDto dto) {
        return Expedition.builder()
                .abbreviation(dto.getAbbreviation())
                .location(dto.getLocation())
                .build();
    }
}
