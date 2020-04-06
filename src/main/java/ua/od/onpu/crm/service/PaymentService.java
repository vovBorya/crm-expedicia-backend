package ua.od.onpu.crm.service;

import ua.od.onpu.crm.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> list();

    PaymentDto create(PaymentDto paymentDto);

    PaymentDto get(Integer id);

    PaymentDto update(Integer id, PaymentDto paymentDto);

    PaymentDto delete(Integer id);

    List<PaymentDto> getPaymentsByDeal(Integer dealId);
}
