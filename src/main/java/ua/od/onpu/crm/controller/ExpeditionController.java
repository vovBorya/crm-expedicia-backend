package ua.od.onpu.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.od.onpu.crm.dto.ExpeditionDto;
import ua.od.onpu.crm.service.ExpeditionService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/expeditions")
public class ExpeditionController {

    private ExpeditionService expeditionService;

    @Autowired
    public ExpeditionController(ExpeditionService expeditionService) {
        this.expeditionService = expeditionService;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<ExpeditionDto> list() {
        List<ExpeditionDto> response = expeditionService.list();
        log.info("GET expeditions: {}", response);
        return response;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public ExpeditionDto create(@RequestBody ExpeditionDto request) {
        ExpeditionDto response = expeditionService.create(request);
        log.info("CREATE expedition {}", response);
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public ExpeditionDto get(@PathVariable Integer id) {
        ExpeditionDto response = expeditionService.get(id);
        log.info("GET expedition by id = {}: {}", id, response);
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ExpeditionDto update(@PathVariable Integer id, @RequestBody ExpeditionDto request) {
        ExpeditionDto response = expeditionService.update(id, request);
        log.info("UPDATE expedition by id = {} : {}", id, response);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ExpeditionDto delete(@PathVariable Integer id) {
        ExpeditionDto response = expeditionService.delete(id);
        log.info("DELETE expedition by id = {}: {}", id, response);
        return response;
    }
}
