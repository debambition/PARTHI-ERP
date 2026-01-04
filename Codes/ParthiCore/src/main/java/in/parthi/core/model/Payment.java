
/**
 * This is a product class that provide the defination of the product and provides all the method
 * thats required to maintain the products.
 *
 * @author Dibakar Deb
 * @since 2025-08-30
 */
package in.parthi.core.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private int paymentId;
	// @Column(name = "product_id")
	// private String productId;
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	@Column(name = "paid_amount")
	private double paidAmount;
	// @Column(name = "customer_number")
	// private String customerNumber;

	@ManyToOne()
    @JoinColumn(name = "customer_number", referencedColumnName = "contact_number")
    private Customer customerNumber;

	@ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDate getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Customer getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	public void setProductId(Product product) {
		this.product = product;
	}

}
