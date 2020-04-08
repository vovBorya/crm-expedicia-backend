package ua.od.onpu.crm.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ua.od.onpu.crm.dao.model.Deal;
import ua.od.onpu.crm.dto.DealDto;

import java.util.List;

public interface DealRepository extends CrudRepository<Deal, Integer> {

    List<Deal> findAllByChildId(Integer childId);

    List<Deal> findAllByCustomerId(Integer customerId);

    List<Deal> findAllByEmployeeId(Integer employeeId);

    List<Deal> findAllByExpeditionId(Integer expeditionId);
}
