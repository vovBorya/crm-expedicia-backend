package ua.od.onpu.crm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.onpu.crm.dao.model.Deal;
import ua.od.onpu.crm.dao.model.Payment;
import ua.od.onpu.crm.dao.repository.DealRepository;
import ua.od.onpu.crm.dao.repository.PaymentRepository;
import ua.od.onpu.crm.dto.PaymentDto;
import ua.od.onpu.crm.exception.ResourceNotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ua.od.onpu.crm.service.DealServiceImpl.logDealNotFound;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private DealRepository dealRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, DealRepository dealRepository) {
        this.paymentRepository = paymentRepository;
        this.dealRepository = dealRepository;
    }

    @Override
    public List<PaymentDto> list() {
        List<PaymentDto> list = new LinkedList<>();
        paymentRepository.findAll().forEach(payment -> list.add(buildToDto(payment)));
        return list;
    }

    @Override
    public PaymentDto create(PaymentDto paymentDto) {
        Payment payment = paymentRepository.save(buildToEntity(paymentDto));
        return buildToDto(payment);
    }

    @Override
    public PaymentDto get(Integer id) {
        Payment payment = findPaymentById(id);
        return buildToDto(payment);
    }

    @Override
    public PaymentDto update(Integer id, PaymentDto paymentDto) {
        Payment payment = findPaymentById(id);

        Deal deal = findDealById(paymentDto.getDealId());

        payment.setDeal(deal);
        payment.setPaidAt(paymentDto.getPaidAt());

        Payment response = paymentRepository.save(payment);
        return buildToDto(response);
    }

    @Override
    public PaymentDto delete(Integer id) {
        Payment payment = findPaymentById(id);
        paymentRepository.delete(payment);
        return buildToDto(payment);
    }

    @Override
    public List<PaymentDto> getPaymentsByDeal(Integer dealId) {
        return paymentRepository.findAllByDealId(dealId)
                .stream()
                .map(this::buildToDto)
                .collect(Collectors.toList());
    }

    static ResourceNotFoundException logPaymentNotFound(Integer id) {
        log.error("Payment with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Payment with id = " + id + " NOT_FOUND");
    }

    private Payment findPaymentById(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> logPaymentNotFound(id));
    }

    private Deal findDealById(Integer id) {
        return dealRepository.findById(id)
                .orElseThrow(() -> logDealNotFound(id));
    }

    private PaymentDto buildToDto(Payment payment) {
        Deal deal = findDealById(payment.getDeal().getId());

        return PaymentDto.builder()
                .id(payment.getId())
                .dealId(deal.getId())
                .paidAt(payment.getPaidAt())
                .sum(payment.getSum())
                .build();
    }

    private Payment buildToEntity(PaymentDto dto) {
        Deal deal = findDealById(dto.getDealId());

        return Payment.builder()
                .deal(deal)
                .paidAt(dto.getPaidAt())
                .sum(dto.getSum())
                .build();
    }
}
