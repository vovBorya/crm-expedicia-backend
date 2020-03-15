package ua.od.onpu.crm.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ua.od.onpu.crm.dao.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
