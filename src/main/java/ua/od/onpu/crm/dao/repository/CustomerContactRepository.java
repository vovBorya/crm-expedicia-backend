package ua.od.onpu.crm.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ua.od.onpu.crm.dao.model.CustomerContact;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Integer> {
}
