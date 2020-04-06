package ua.od.onpu.crm.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ua.od.onpu.crm.dao.model.CustomerContact;

import java.util.List;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Integer> {

    List<CustomerContact> findAllByCustomerId(Integer customerId);
}
