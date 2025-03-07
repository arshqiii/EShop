package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> voucherDetails;
    private Map<String, String> cashOnDeliveryDetails;
    private Order customerOrder;
    List<Product> itemList;

    @BeforeEach
    void setUp() {
        this.voucherDetails = new HashMap<>();
        this.voucherDetails.put("voucherCode", "ESHOP1234ABC5678");
        this.cashOnDeliveryDetails = new HashMap<>();
        this.cashOnDeliveryDetails.put("address", "Jl. Jagakarsa 1, No. 23");
        this.cashOnDeliveryDetails.put("deliveryFee", "25000");
        this.itemList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        Product product2 = new Product();
        product2.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(1);
        this.itemList.add(product1);
        this.itemList.add(product2);
        this.customerOrder = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.itemList, 1708560000L, "Safira Sudrajat");

    }

    @Test
    void testCreatePaymentWithoutData(){
        assertThrows(IllegalArgumentException.class,
                () -> new Payment("a5e93216-127c-43df-b7f1-89b720e496bb",
                        "VOUCHER", null, this.customerOrder));
    }

    @Test
    void testCreatePaymentWithValidVoucherDetails() {
        Payment payment = new Payment("a5e93216-127c-43df-b7f1-89b720e496bb",
                "VOUCHER", this.voucherDetails, this.customerOrder);
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(this.voucherDetails, payment.getPaymentData());
        assertEquals(this.customerOrder, payment.getOrder());
    }

    @Test
    void testCreatePaymentWithInvalidVoucherPrefix() {
        Map<String, String> voucherWithoutPrefix = new HashMap<>();
        voucherWithoutPrefix.put("voucherCode", "1234567890XYZABC");
        Payment payment = new Payment("a5e93216-127c-43df-b7f1-89b720e496bb",
                "VOUCHER", voucherWithoutPrefix, this.customerOrder);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithMissingNumberOnVoucher() {
        Map<String, String> voucherMissingNumber = new HashMap<>();
        voucherMissingNumber.put("voucherCode", "ESHOP1234WESHXYZ");
        Payment payment = new Payment("a5e93216-127c-43df-b7f1-89b720e496bb",
                "VOUCHER", voucherMissingNumber, this.customerOrder);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidVoucherLength() {
        Map<String, String> voucherTooShort = new HashMap<>();
        Map<String, String> voucherTooLong = new HashMap<>();
        voucherTooShort.put("voucherCode", "ESHOP123456");
        voucherTooLong.put("voucherCode", "ESHOP1234ABC5678D");
        Payment payment1 = new Payment("a5e93216-127c-43df-b7f1-89b720e496bb",
                "VOUCHER", voucherTooShort, this.customerOrder);
        Payment payment2 = new Payment("7148aec0-26ea-47a1-aafe-c9eb598985f8",
                "VOUCHER", voucherTooLong, this.customerOrder);
        assertEquals("REJECTED", payment1.getStatus());
        assertEquals("REJECTED", payment2.getStatus());
    }

    @Test
    void testCreatePaymentWithValidCashOnDeliveryDetails() {
        Payment payment = new Payment("7148aec0-26ea-47a1-aafe-c9eb598985f8",
                "CASH_ON_DELIVERY", this.cashOnDeliveryDetails, this.customerOrder);
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(this.cashOnDeliveryDetails, payment.getPaymentData());
        assertEquals(this.customerOrder, payment.getOrder());
    }

    @Test
    void testCreatePaymentWithMissingAddress() {
        Map<String, String> codWithoutAddress = new HashMap<>();
        codWithoutAddress.put("address", "");
        codWithoutAddress.put("deliveryFee", "25000");
        Payment payment1 = new Payment("7148aec0-26ea-47a1-aafe-c9eb598985f8",
                "CASH_ON_DELIVERY", codWithoutAddress, this.customerOrder);
        assertEquals("REJECTED", payment1.getStatus());
    }

    @Test
    void testCreatePaymentWithMissingDeliveryFee() {
        Map<String, String> codWithoutFee = new HashMap<>();
        codWithoutFee.put("address", "Jalan Depok");
        codWithoutFee.put("deliveryFee", "");
        Payment payment1 = new Payment("7148aec0-26ea-47a1-aafe-c9eb598985f8",
                "CASH_ON_DELIVERY", codWithoutFee, this.customerOrder);
        assertEquals("REJECTED", payment1.getStatus());
    }

    @Test
    void testCreatePaymentWithoutOrder(){
        assertThrows(IllegalArgumentException.class,
                () -> new Payment("a5e93216-127c-43df-b7f1-89b720e496bb",
                        "VOUCHER", this.voucherDetails, null));
    }

    @Test
    void testCreatePaymentWithInvalidMethod() {
        assertThrows(IllegalArgumentException.class,
                () -> new Payment("a5e93216-127c-43df-b7f1-89b720e496bb",
                        "GRATIS", this.voucherDetails, null));
    }

    @Test
    void testSetMethodToInvalidMethod(){
        Payment payment = new Payment("a5e93216-127c-43df-b7f1-89b720e496bb",
                "VOUCHER", this.voucherDetails, this.customerOrder);
        assertThrows(IllegalArgumentException.class,
                () -> payment.setStatus("GRATIS"));
    }
}