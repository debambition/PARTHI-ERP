package in.parthi.core.repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.core.model.transaction.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TransactionRepo {
    private static final Logger logger = LoggerFactory.getLogger(TransactionRepo.class);
    

    /**
    * This method take a an procuct id and retrieve the product from the database.
    * 
    * @param String id with which the product needs to be found
    * @return Returns the product
    * @throws RuntimeException if the product is unavailable in the database.
    */


     /**
     * This method take a an transaction id and retrieve the transaction from the database.
     * 
     * @param String id with which the transaction needs to be found
     * @return Returns the transaction
     * @throws RuntimeException if the transaction is unavailable in the database.
     */
    
     public Transaction getTransaction(String id) throws RuntimeException {// Create a NotFound Exception
        Transaction transaction = null;
         EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Logistic");
         EntityManager entityManager = entityManagerFactory.createEntityManager();
         transaction = entityManager.find(Transaction.class, id);

         entityManager.close();
         entityManagerFactory.close();
       
         
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
        String response = "";
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Logistic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //add transaction and save to db
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
        response = "Transaction added successfully";
         logger.info("Transaction with id: " + transaction.getId() + " added successfully");
        entityManager.close();
        entityManagerFactory.close();
        return response;

        
    }
}

