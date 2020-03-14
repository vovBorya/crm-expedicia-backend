package ua.od.onpu.crm.dao.model;

public enum Status {
    CREATED(1),
    PREPAID_EXPANSE(2),
    PARTIAL_PAYMENT(3),
    FULLY_PAID(4),
    COMPLETED(5),
    CANCELED(6);

    private int code;

    Status(int code) {
        this.code = code;
    }
}
