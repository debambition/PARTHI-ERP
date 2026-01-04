package in.parthi.core.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.common.Properties;
import in.parthi.core.model.Payment;
import in.parthi.core.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;

public class PaymentRepo {
    private static final Logger logger = LoggerFactory.getLogger(PaymentRepo.class);



    /**
     * This method take a transaction details and add it to the database.
     * 
     * @param Transaction that need to be added to the database
     * @return Returns the choice of the user
     * @throws RuntimeException if the product is already available in the database.
     */
    public String addPayment(Payment payment) throws RuntimeException {// Create a NotFound Exception
        logger.info("Adding payment for product ID " + payment.getProduct().getId());
        String response = "";
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Parthi-IND");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // add transaction and save to db
        entityManager.persist(payment);
        entityManager.getTransaction().commit();
        response = "Payment added successfully for product " + payment.getPaymentId();
        logger.info("Payment with id: " + payment.getPaymentId() + " added successfully");
        entityManager.close();
        entityManagerFactory.close();
        return response;


    }


    /**
     * This method take a product Id and execute the return product logic.
     * 
     * @param String Product Id that need to be returned
     * @return Returns Deposite amount for customer
     * @throws RuntimeException if the product is already available in the database.
     */
    public String returnFromCustomer(String productId) throws RuntimeException {// Create a NotFound Exception
        String response = "";
        try {
            logger.info("Returning product from customer with product id " + productId);
            EntityManager entityManager = Properties.getDBConnection();
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("dbo.return_product");
            query.registerStoredProcedureParameter("Product_ID", String.class, jakarta.persistence.ParameterMode.IN);
            query.setParameter("Product_ID", productId);
            query.execute();
            double deposite = (Double) query.getResultList().get(0);
            response = "Return registered successfully. Customer has a deposite of Rs. " + deposite;
            logger.info(response);

        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            throw ex;
        }
        return response;


    }

    /**
     * This method take a an procuct id and retrieve the sum of all the payment from the database.
     * 
     * @param String id with which the product needs to be found
     * @return sum of all the payment
     */
    public double getPaidAmount(Product product) {
        logger.info("Retrieving paid amount for product " + product.getId());
        try {
            EntityManager entityManager = Properties.getDBConnection();
            TypedQuery<Double> query = entityManager.createQuery("SELECT SUM(p.paidAmount)  FROM Payment p WHERE p.product  = :product GROUP BY p.product", Double.class);
            query.setParameter("product", product);
            Object result = query.getSingleResult();
            Double paidAmount = (result != null) ? ((Number) result).doubleValue() : 0.0;
            logger.info("Paid amount received for product " + product.getId());
            return paidAmount;
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            throw ex;
        }


    }
}

