
/**
 * This is a Transaction class that provide the defination of the transaction and provides all the
 * method thats required to maintain the transaction.
 *
 * @author Abhishek Ray
 * @since 2025-08-30
 */

package in.parthi.core.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_id")
    private int id;
    @Column(name = "invoice")
    private String invoice;
    @Column(name = "particulars")
    private String particular;
    @Column(name = "txn_type", length = 50)
    private String txnType;
    @Column(name = "category")
    private String txnCategory;
    @Column(name = "txn_mode", length = 20)
    private String paymentMode;
    @Column(name = "description")
    private String description;
    @Column(name = "Amount")
    private double amount;
    @Column(name = "txn_date")
    LocalDate transactionDate;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoice() {
        return this.invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getParticular() {
        return this.particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public String getTxnType() {
        return this.txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTxnCategory() {
        return this.txnCategory;
    }

    public void setTxnCategory(String txnCategory) {
        this.txnCategory = txnCategory;
    }

    public String getPaymentMode() {
        return this.paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String toString() {
        return "Transaction date: " + this.getTransactionDate() + "\n" + "Transaction Category: " + this.getTxnCategory() + "\n" + "ID: " + this.getId() + "\n" + "Invoice Id:" + this.getInvoice()
                + "\n" + "Perticular: " + this.getParticular() + "\n" + "Transaction type: " + this.getTxnType() + "\n" + "Payment mode: " + this.getPaymentMode() + "\n" + "Amount paid: "
                + this.getAmount() + "\n";
    }

}
