package in.parthi.core.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.parthi.core.model.Payment;
import in.parthi.core.repository.CustomerRepo;
import in.parthi.core.repository.PaymentRepo;
import in.parthi.core.repository.ProductRepo;

public class PaymentService {
	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
	PaymentRepo paymentRepo = new PaymentRepo();
	CustomerRepo customerRepo = new CustomerRepo();
	ProductRepo productRepo = new ProductRepo();

	/**
	 * This method take payment object and add it to the database.
	 * 
	 * @param Payment payment
	 * @return Status response of the insertion execution
	 * @throws Exception
	 */
	public String addPayment(Payment payment) throws Exception {
		String response = "";
		try {
			if (payment.getPaymentDate().isAfter(LocalDate.now()))
				throw new Exception("Payment date cannot be a future date");

			response = paymentRepo.addPayment(payment);

		} catch (Exception e) {
			response = e.getLocalizedMessage();
			logger.error("Exception occured while adding payment: " + e.getLocalizedMessage());
			throw e;
		}
		return response;
	}

	public String returnFromCustomer(String productId) throws Exception {
		String response = "";
		try {
			response = paymentRepo.returnFromCustomer(productId);

		} catch (Exception e) {
			logger.error("Exception occured while retruning customer product: " + e.getLocalizedMessage());
			throw e;
		}
		return response;
	}

	public double getPaidAmount(String productId) throws Exception {
		double amount = 0;
		try {

			amount = paymentRepo.getPaidAmount(productRepo.getProduct(productId));

		} catch (Exception e) {
			logger.error("Exception occured while retrieving paid amount: " + e.getLocalizedMessage());
			throw e;
		}
		return amount;
	}
}

