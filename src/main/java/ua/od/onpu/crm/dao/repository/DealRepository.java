package ua.od.onpu.crm.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.od.onpu.crm.dao.model.Deal;

import java.util.List;

public interface DealRepository extends CrudRepository<Deal, Integer> {

    List<Deal> findAllByChildId(Integer childId);

    List<Deal> findAllByCustomerId(Integer customerId);

    List<Deal> findAllByEmployeeId(Integer employeeId);

    List<Deal> findAllByExpeditionId(Integer expeditionId);

    @Query(value =
            "SELECT * FROM deals WHERE status LIKE :dealStatus " +
                "AND IF(:expeditionId is NULL, true, expedition_id = :expeditionId)",
            nativeQuery = true)
    List<Deal> getFilteredDeal(@Param("dealStatus") String dealStatus,
                               @Param("expeditionId") Integer id);
}
