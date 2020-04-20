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
                    "AND IF(:employeeName = '', TRUE, employee_id = " +
                        "(SELECT id FROM employees " +
                            "WHERE CONCAT_WS(' ', last_name, first_name, patronymic) LIKE :employeeName)) " +

                    "AND IF(:customerName = '', TRUE, customer_id = " +
                        "(SELECT id FROM customers " +
                            "WHERE CONCAT_WS(' ', last_name, first_name, patronymic) LIKE :customerName)) " +

                    "AND IF(:childName = '', TRUE, child_id = " +
                        "(SELECT id FROM children " +
                            "WHERE CONCAT_WS(' ', last_name, first_name, patronymic) LIKE :childName)) ",
            nativeQuery = true)
    List<Deal> getFilteredDeal(@Param("dealStatus") String dealStatus,
                               @Param("expeditionId") Integer id,
                               @Param("employeeName") String employeeName,
                               @Param("customerName") String customerName,
                               @Param("childName") String childName);
}
