package in.parthi.core.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.common.Properties;
import in.parthi.core.model.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class TransactionRepo {
    private static final Logger logger = LoggerFactory.getLogger(TransactionRepo.class);

    /**
     * This method take a an transaction id and retrieve the transaction from the database.
     * 
     * @param String id with which the transaction needs to be found
     * @return Returns the transaction
     * @throws RuntimeException if the transaction is unavailable in the database.
     */

    public Transaction getTransaction(int id) throws RuntimeException {// Create a NotFound Exception
        logger.info("retrieving transaction with ID "+id);
        Transaction transaction = null;
        EntityManager entityManager = Properties.getDBConnection();
        transaction = entityManager.find(Transaction.class, id);
        logger.info("transaction with ID "+id+" retrive successfully");
        return transaction;

    }

    /**
     * This method take a transaction details and add it to the database.
     * 
     * @param Transaction that need to be added to the database
     * @return Returns the choice of the user
     * @throws RuntimeException if the product is already available in the database.
     */
    public String addTransaction(Transaction transaction) throws RuntimeException {// Create a NotFound Exception
        logger.info("Adding transaction for "+transaction.getTxnCategory());
        String response = "";
        EntityManager entityManager = Properties.getDBConnection();
        entityManager.getTransaction().begin();

        // add transaction and save to db
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
        response = "Transaction added successfully";
        logger.info("Transaction added successfully");
        return response;


    }

    /**
     * This method take a an invoice and retrieve transaction from the database.
     * 
     * @param String id with which the product needs to be found
     * @return sum of all the payment
     */
    public Transaction getTxnByInvoice(String invoice) {
        logger.info("retrieving transaction with invoice ID "+invoice);
        Transaction transaction = null;
        try{
            EntityManager entityManager = Properties.getDBConnection();
        TypedQuery<Transaction> query = entityManager.createQuery("SELECT txn FROM Transaction txn WHERE txn.invoice = :invoice", Transaction.class);
        query.setParameter("invoice", invoice);
        Object result = query.getSingleResult();
        transaction = (Transaction) result;
        logger.info("Transaction retrieve successfully");
        }catch(Exception ex){
            logger.warn("Transaction with invoice "+invoice+" not found");
        }

        return transaction;

    }

    public String updateTransaction(Transaction transaction) throws RuntimeException {
        logger.info("Updating transaction with invoice ID "+transaction.getId());
        //
        EntityManager entityManager = Properties.getDBConnection();
        entityManager.getTransaction().begin();
        String response = "";
        // customer = entityManager.find(Customer.class, id);

        // customer and save to db
        entityManager.merge(transaction);
        entityManager.getTransaction().commit();
        response = "Transaction with invoice : " + transaction.getInvoice() + " updated in the database";
        logger.info(response);

        return response;

    }

}

