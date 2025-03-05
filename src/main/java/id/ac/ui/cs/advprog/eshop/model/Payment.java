package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethods;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    String status;
    Order order;

    public Payment(String method, Map<String, String> paymentData, Order order) {
        if (order == null || paymentData == null) {
            throw new IllegalArgumentException();
        }

        this.id = UUID.randomUUID().toString();
        this.order = order;
        this.paymentData = paymentData;

        if (!PaymentMethods.contains(method)) {
            throw new IllegalArgumentException();
        }
        this.method = method;

        if (method.equals(PaymentMethods.VOUCHER.getValue())) {
            validateVoucherPayment();
        } else if (method.equals(PaymentMethods.CASH_ON_DELIVERY.getValue())) {
            validateCashOnDeliveryPayment();
        }
    }

    private void validateVoucherPayment() {
        String voucher = paymentData.get("voucherCode");
        if ((voucher != null && voucher.length() == 16) && voucher.startsWith("ESHOP") && countDigit(voucher) == 8) {
            this.status = PaymentStatus.SUCCESS.getValue();
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    private void validateCashOnDeliveryPayment() {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");
        if (address != null && !address.isEmpty() && deliveryFee != null && !deliveryFee.isEmpty()) {
            this.status = PaymentStatus.SUCCESS.getValue();
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    public void setStatus(String status) {
        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException();
        }
        this.status = status;
    }

    private int countDigit(String code){
        int count = 0;
        for (int i = 0; i < code.length(); i++) {
            if (Character.isDigit(code.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}