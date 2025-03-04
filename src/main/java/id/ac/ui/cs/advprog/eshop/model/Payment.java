package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter

public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    String status;
    Order order;

    public Payment(String method, Map<String, String> paymentData, Order order) {
        if (order == null && paymentData == null) {
            throw new IllegalArgumentException();
        }
        this.id = UUID.randomUUID().toString();
        this.order = order;
        this.paymentData = paymentData;

        if (!method.equals("VOUCHER") || !method.equals("CASH_ON_DELIVERY")) {
            throw new IllegalArgumentException();
        }
        this.method = method;
    }
}