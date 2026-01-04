package in.parthi.core.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.common.PaymentMode;
import in.parthi.common.TransactionCategory;
import in.parthi.common.TransactionType;
import in.parthi.core.model.AddProductTransaction;
import in.parthi.core.model.Customer;
import in.parthi.core.model.Payment;
import in.parthi.core.model.Product;
import in.parthi.core.model.Transaction;
import in.parthi.core.repository.CustomerRepo;
import in.parthi.core.repository.ProductRepo;
import in.parthi.core.repository.TransactionRepo;

public class TransactionService {
	private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
	TransactionRepo transactionRepo = new TransactionRepo();
	PaymentService paymentService = new PaymentService();
	CustomerRepo customerRepo = new CustomerRepo();
	ProductRepo productRepo = new ProductRepo();


	/**
	 * This method take sales or installment transactin along with customer details and add it to the
	 * database.
	 * 
	 * @param Transaction that need to be added to the database
	 * @return Returns the Response msg of the user
	 * @throws Exception
	 */
	public String addSaleTransaction(Transaction transaction, Customer tmpCustomer, double sellingPrice) throws Exception {
		String response = "";
		Customer customer = null;
		try {
			if (transaction.getTransactionDate().isAfter(LocalDate.now()))
				throw new Exception("Transaction date cannot be a future date");


			if (transaction.getTxnCategory().equalsIgnoreCase(TransactionCategory.SALES.toString())) {

				// add customer if the customer is new or update the deposite if required.
				customer = customerRepo.getCustomer(tmpCustomer.getCustomerNumber());
				if (customer == null) {
					customerRepo.addCustomer(tmpCustomer);
					customer = tmpCustomer;
				} else {
					customer = tmpCustomer;

					// Enter a transaction with debit the deposite amount in transaction
					if (customer.getDepositeAmount() > 0) {
						Transaction withdraw_deposite = new Transaction();
						if (transaction.getAmount() > customer.getDepositeAmount()) {

							withdraw_deposite.setAmount(customer.getDepositeAmount());
							customer.setDepositeAmount(0);
						} else {
							withdraw_deposite.setAmount(transaction.getAmount());
							customer.setDepositeAmount(customer.getDepositeAmount() - transaction.getAmount());
						}
						withdraw_deposite.setTransactionDate(LocalDate.now());
						withdraw_deposite.setDescription("Withdraw amount from deposte for " + transaction.getParticular() + " by " + customer.getName());
						withdraw_deposite.setPaymentMode(PaymentMode.ONLINE.toString());
						withdraw_deposite.setTxnCategory(TransactionCategory.DEPOSITE.toString());
						withdraw_deposite.setTxnType(TransactionType.DEBIT.toString());
						addTransaction(withdraw_deposite);
					}
					customerRepo.updateCustomer(customer);
				}

				// Update the product status to sold, scustomerelling price, checkout date
				ProductService productService = new ProductService();
				productService.updateSoldProduct(transaction.getParticular(), transaction.getTransactionDate(), sellingPrice);



			} else {
				customer = tmpCustomer;
			}
			// make a entry in payment
			Payment payment = new Payment();
			payment.setPaymentDate(transaction.getTransactionDate());
			payment.setPaidAmount(transaction.getAmount());
			payment.setCustomerNumber(customer);
			Product product = productRepo.getProduct(transaction.getParticular());
			payment.setProductId(product);
			paymentService.addPayment(payment);


			response = transactionRepo.addTransaction(transaction);

		} catch (Exception e) {
			response = e.getLocalizedMessage();
			logger.error("Exception occured while adding Transaction: " + e.getLocalizedMessage());
			throw e;
		}
		return response;
	}

	/**
	 * This method is used to add transaction for add Product database.
	 * 
	 * @param invoiceID and product ID that need to be added to the database
	 * @return Returns the Response msg of the user
	 * @throws Exception
	 */
	public String addProductTransaction(AddProductTransaction addProductTransaction) {
		String response = "";
		Transaction transaction = transactionRepo.getTxnByInvoice(addProductTransaction.getInvoice());
		try {
			if (transaction == null) {
				transaction = new Transaction();
				transaction.setInvoice(addProductTransaction.getInvoice());
				transaction.setParticular(addProductTransaction.getProductId());
				transaction.setTxnType(TransactionType.DEBIT.toString());
				transaction.setTxnCategory(TransactionCategory.PRODUCT_COST.getLabel());
				transaction.setPaymentMode(addProductTransaction.getPaymentMode());
				transaction.setAmount(addProductTransaction.getAmount());
				transaction.setTransactionDate(addProductTransaction.getTransactionDate());
				transaction.setDescription("Product purchase from " + addProductTransaction.getVendor());
				response = transactionRepo.addTransaction(transaction);

			} else {
				transaction.setParticular(transaction.getParticular() + "," + addProductTransaction.getProductId());
				transaction.setAmount(transaction.getAmount() + addProductTransaction.getAmount());
				response = transactionRepo.updateTransaction(transaction);
			}


		} catch (Exception e) {
			response = e.getLocalizedMessage();
			logger.error("Exception while transaction for product : " + response);
		}
		return response;
	}

	/**
	 * This method take a Transaction details and add it to the database.
	 * 
	 * @param Transaction that need to be added to the database
	 * @return Returns the Response msg of the user
	 * @throws Exception
	 */
	public String addTransaction(Transaction transaction) throws Exception {
		String response = "";
		try {
			if (transaction.getTransactionDate().isAfter(LocalDate.now()))
				throw new Exception("Transaction date cannot be a future date");

			response = transactionRepo.addTransaction(transaction);

		} catch (Exception e) {
			response = e.getLocalizedMessage();
			logger.error("Exception occured while adding Transaction: " + e.getLocalizedMessage());
			throw e;
		}
		return response;
	}

	/**
	 * This method take a a transaction id and retrieve the transaction from the database.
	 * 
	 * @param int id with which the transaction needs to be found
	 * @return Returns a transaction
	 * @throws RuntimeException if the transaction is unavailable in the database.
	 */
	public Transaction getTransaction(int id) {
		Transaction transaction = null;
		try {
			transaction = transactionRepo.getTransaction(id);
			if (transaction == null) {
				logger.info("transaction with id: " + id + " found in database");
			}

		} catch (RuntimeException e) {
			logger.error("Exception occured while retriving the  transaction: " + e.getLocalizedMessage());

		}
		return transaction;
	}
}

