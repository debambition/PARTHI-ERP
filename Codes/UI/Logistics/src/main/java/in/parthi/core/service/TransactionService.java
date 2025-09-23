package in.parthi.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.core.model.transaction.Transaction;
import in.parthi.core.repository.TransactionRepo;

public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    TransactionRepo transactionRepo = new TransactionRepo();

    /**
     * This method take a Transaction details and add it to the database.
     * 
     * @param Transaction that need to be added to the database
     * @return Returns the Responce msg of the user
     */
    public String addTransaction(Transaction transaction) {
        String response = "";
        Transaction temTransaction = null;
        try {
            // check the transactionb id is already there or not
            temTransaction = this.getTransaction(transaction.getId());
            if (temTransaction == null) {
                response = transactionRepo.addTransaction(transaction);
            }

        } catch (RuntimeException e) {
            response = e.getLocalizedMessage();
            logger.error("Exception occured while adding Transaction: " + e.getLocalizedMessage());
        }
        return response;
    }

    /**
     * This method take a a transaction id and retrieve the transaction from the database.
     * 
     * @param String id with which the transaction needs to be found
     * @return Returns a transaction
     * @throws RuntimeException if the transaction is unavailable in the database.
     */
    public Transaction getTransaction(String id) {
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

