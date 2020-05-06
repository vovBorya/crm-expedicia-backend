package ua.od.onpu.crm.service;

import org.springframework.web.bind.annotation.RequestParam;
import ua.od.onpu.crm.dto.DealDto;

import java.util.List;

public interface DealService {

    List<DealDto> list();

    DealDto create(DealDto dealDto);

    DealDto get(Integer id);

    DealDto update(Integer id, DealDto dealDto);

    DealDto delete(Integer id);

    List<DealDto> getDealsByChild(Integer childId);

    List<DealDto> getDealsByCustomer(Integer customerId);

    List<DealDto> getDealsByEmployee(Integer employeeId);

    List<DealDto> getDealsByExpedition(Integer expeditionId);

    List<DealDto> getFilteredDeal(String status, Integer expeditionId, Integer startSum, Integer endSum,
                                  Integer employeeId, Integer customerId, Integer childId, String sleepingBag);
}
