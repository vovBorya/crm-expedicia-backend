package ua.od.onpu.crm.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.od.onpu.crm.dao.model.Child;

import java.util.List;

public interface ChildRepository extends CrudRepository<Child, Integer> {

    List<Child> findAllByParentId(Integer parentId);

    @Query(value =
            "SELECT * FROM children " +
                    "WHERE CONCAT_WS(' ', last_name, first_name, patronymic) LIKE :fullName " +
                    "AND year(curdate()) - year(birth_day) between :startAge and :endAge " +
                    "AND IF(:parentId is NULL, true, parent_id = :parentId)",
            nativeQuery = true)
    List<Child> getFilteredChildren(@Param("fullName") String fullName,
                                    @Param("startAge") Integer startAge,
                                    @Param("endAge") Integer endAge,
                                    @Param("parentId") Integer parentId);
}
