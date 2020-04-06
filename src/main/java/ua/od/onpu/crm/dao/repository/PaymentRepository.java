package ua.od.onpu.crm.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ua.od.onpu.crm.dao.model.Payment;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

    List<Payment> findAllByDealId(Integer dealId);
}
