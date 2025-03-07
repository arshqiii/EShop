package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethods;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository;

    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        List<Product> products = new ArrayList<>();
        payments = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products.add(product1);

        Map<String, String> cashOnDeliveryDetails = new HashMap<>();
        cashOnDeliveryDetails.put("address", "Jl. Jagakarsa 1, No. 23");
        cashOnDeliveryDetails.put("deliveryFee", "25000");

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        Order order2 = new Order("13652996-013a-4c07-b546-54eb1396d79b",
                products, 1708570000L, "Bambang Sudrajat");
        Payment payment1 = new Payment("a5e93216-127c-43df-b7f1-89b720e496bb","VOUCHER", Map.of("voucherCode", "ESHOP1234ABC5678"), order1);
        Payment payment2 = new Payment("7148aec0-26ea-47a1-aafe-c9eb598985f8","CASH_ON_DELIVERY", cashOnDeliveryDetails, order2);
        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testSavePayment() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById(result.getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getMethod(), findResult.getMethod());
    }

    @Test
    void testUpdatePayment() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        Map<String, String> newPaymentDetail = new HashMap<>();
        newPaymentDetail.put("address", "Jl. Jagakarsa 1, No. 23");
        newPaymentDetail.put("deliveryFee", "25000");
        Payment newPayment = new Payment("7148aec0-26ea-47a1-aafe-c9eb598985f8",
                "CASH_ON_DELIVERY", newPaymentDetail, payment.getOrder());
        Payment result = paymentRepository.save(newPayment);
        Payment findResult = paymentRepository.findById(result.getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(PaymentMethods.CASH_ON_DELIVERY.getValue(), findResult.getMethod());
        assertEquals(payment.getOrder(), findResult.getOrder());
    }

    @Test
    void testFindPaymentByIdAndFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findPayment = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findPayment.getId());
        assertEquals(payments.get(1).getPaymentData(), findPayment.getPaymentData());
        assertEquals(payments.get(1).getOrder(), findPayment.getOrder());
        assertEquals(payments.get(1).getStatus(), findPayment.getStatus());
        assertEquals(payments.get(1).getMethod(), findPayment.getMethod());
    }

    @Test
    void testFindPaymentByIdAndNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findPayment = paymentRepository.findById("abcde");
        assertNull(findPayment);
    }

    @Test
    void testFindAllPaymentsIfEmpty() {
        Iterator<Payment> paymentIterator = paymentRepository.findAll();
        assertFalse(paymentIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOnePayment() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Iterator<Payment> paymentIterator = paymentRepository.findAll();
        assertTrue(paymentIterator.hasNext());
        Payment firstPayment = paymentIterator.next();
        assertEquals(payments.get(0).getId(), firstPayment.getId());
        assertEquals(payments.get(0).getPaymentData(), firstPayment.getPaymentData());
        assertEquals(payments.get(0).getOrder(), firstPayment.getOrder());
        assertEquals(payments.get(0).getStatus(), firstPayment.getStatus());
        assertEquals(payments.get(0).getMethod(), firstPayment.getMethod());
        Payment secondPayment = paymentIterator.next();
        assertEquals(payments.get(1).getId(), secondPayment.getId());
        assertEquals(payments.get(1).getPaymentData(), secondPayment.getPaymentData());
        assertEquals(payments.get(1).getOrder(), secondPayment.getOrder());
        assertEquals(payments.get(1).getStatus(), secondPayment.getStatus());
        assertEquals(payments.get(1).getMethod(), secondPayment.getMethod());
        assertFalse(paymentIterator.hasNext());
    }
}