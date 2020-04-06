package ua.od.onpu.crm.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ua.od.onpu.crm.dao.model.Child;

import java.util.List;

public interface ChildRepository extends CrudRepository<Child, Integer> {

    List<Child> findAllByParentId(Integer parentId);
}
