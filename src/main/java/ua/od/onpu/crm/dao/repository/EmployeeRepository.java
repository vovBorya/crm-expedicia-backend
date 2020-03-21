package ua.od.onpu.crm.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ua.od.onpu.crm.dao.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
