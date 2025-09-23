/**
 * This is a customer class that provide the defination of the product and provides all the method
 * thats required to maintain the products.
 *
 * @author Moumita Chatterjee
 * @since 2025-09-10
 */



package in.parthi.core.model.voucher;

import java.time.LocalDate;

public class Voucher {
    private String id;
    private double amount;
    LocalDate startDate;
    LocalDate expiryDate;



    public Voucher() {
    }


    public Voucher(String id, double amount, LocalDate startDate, LocalDate expiryDate) {
        this.id = id;
        this.amount = amount;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }


}
