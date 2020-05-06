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
                    "AND IF(:expeditionId is NULL, TRUE, expedition_id = :expeditionId)" +
                    "AND sum BETWEEN :startSum and :endSum " +
                    "AND IF(:employeeId is NULL, TRUE, employee_id = :employeeId)" +
                    "AND IF(:customerId is NULL, TRUE, customer_id = :customerId)" +
                    "AND IF(:childId is NULL, TRUE, child_id = :childId)" +
                    "AND IF(:sleepingBag = '', TRUE, IF(:sleepingBag = 'true', sleeping_bag = true, sleeping_bag = false))",
            nativeQuery = true)
    List<Deal> getFilteredDeal(@Param("dealStatus") String dealStatus,
                               @Param("expeditionId") Integer expeditionId,
                               @Param("startSum") Integer startSum,
                               @Param("endSum") Integer endSum,
                               @Param("employeeId") Integer employeeId,
                               @Param("customerId") Integer customerId,
                               @Param("childId") Integer childId,
                               @Param("sleepingBag") String sleepingBag);
}
