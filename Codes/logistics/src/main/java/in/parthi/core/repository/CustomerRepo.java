package in.parthi.core.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.common.Properties;
import in.parthi.core.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CustomerRepo {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepo.class);

    /**
     * This method take a an procuct id and retrieve the customer from the database.
     * 
     * @param String id with which the customer needs to be found
     * @return Returns the customer
     * @throws RuntimeException if the customer is unavailable in the database.
     */
    public Customer getCustomer(String customerNumber) {
        logger.info("Getting customer with contact number: " + customerNumber);
        Customer customer = null;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Parthi-IND");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        customer = entityManager.find(Customer.class, customerNumber);

        logger.info("Query executed successfully ");
        return customer;

    }

    /**
     * This method take a customer details and add it to the database.
     * 
     * @param Customer that need to be added to the database
     * @return Returns the choice of the user
     * @throws RuntimeException if the customer is already available in the database.
     */
    public String addCustomer(Customer customer) throws RuntimeException {// Create a NotFound Exception
        logger.info("Adding customer to database: " + customer.toString());
        //
        EntityManager entityManager = Properties.getDBConnection();
        entityManager.getTransaction().begin();
        String response = "";

        // add customer and save to db
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        response = "Customer added successfully";
        logger.info("Customer with id: " + customer.getCustomerNumber() + " added to the database");

        return response;
    }

    /**
     * This method take a customer id and check with the database.
     * 
     * @param Customerid that needs to be checked to the database
     * @return Returns responce
     * @throws RuntimeException if the customer is not available in the database.
     */
    public String updateCustomer(Customer customer) throws RuntimeException {
        logger.info("Updating customer " + customer.toString());
        //
        EntityManager entityManager = Properties.getDBConnection();
        entityManager.getTransaction().begin();
        String response = "";
        // customer = entityManager.find(Customer.class, id);

        // customer and save to db
        entityManager.merge(customer);
        entityManager.getTransaction().commit();
        response = "Customer with id: " + customer.getCustomerNumber() + " updated in the database";
        logger.info(response);

        return response;

    }

}
