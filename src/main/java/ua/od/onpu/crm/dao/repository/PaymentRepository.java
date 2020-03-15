package ua.od.onpu.crm.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ua.od.onpu.crm.dao.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}
