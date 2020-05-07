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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.od.onpu.crm.dto.ChildDto;
import ua.od.onpu.crm.service.ChildService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/children")
public class ChildController {

    private ChildService childService;

    @Autowired
    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<ChildDto> list() {
        List<ChildDto> response = childService.list();
        log.info("GET children: {}", response);
        return response;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public ChildDto create(@RequestBody ChildDto request) {
        ChildDto response = childService.create(request);
        log.info("CREATE child {}", response);
        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ChildDto get(@PathVariable Integer id) {
        ChildDto response = childService.get(id);
        log.info("GET child by id = {}: {}", id, response);
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ChildDto update(@PathVariable Integer id, @RequestBody ChildDto request) {
        ChildDto response = childService.update(id, request);
        log.info("UPDATE child by id = {} : {}", id, response);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public ChildDto delete(@PathVariable Integer id) {
        ChildDto response = childService.delete(id);
        log.info("DELETE child by id = {}: {}", id, response);
        return response;
    }

    @GetMapping(params = "parentId")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<ChildDto> getChildrenByParent(@RequestParam Integer parentId) {
        List<ChildDto> response = childService.getChildrenByParent(parentId);
        log.info("GET children by parentId = {}: {}", parentId, response);
        return response;
    }

    @GetMapping(params = {"fullName", "ageBetween", "parentId"})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    public List<ChildDto> getChildrenByParent(@RequestParam String fullName,
                                              @RequestParam(defaultValue = "0,100") String[] ageBetween,
                                              @RequestParam Integer parentId) {
        List<ChildDto> response = childService.getFilteredChildren(fullName, Integer.parseInt(ageBetween[0]),
                Integer.parseInt(ageBetween[1]), parentId);
        log.info("GET children by filter = fullName - {}, ageBetween - {}, parentId - {}: {}",
                fullName, ageBetween, parentId, response);
        return response;
    }
}
