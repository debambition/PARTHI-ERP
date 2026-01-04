package in.parthi.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.parthi.core.model.Customer;
import in.parthi.core.repository.CustomerRepo;

public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    CustomerRepo customerRepo = new CustomerRepo();


    /**
     * This method take a an procuct id and retrieve the customer from the database.
     * 
     * @param String id with which the customer needs to be found
     * @return Returns the customer
     * @throws RuntimeException if the customer is unavailable in the database.
     */
    public Customer getCustomer(String customerNumber) {
        Customer customer = null;
        try {
            customer = customerRepo.getCustomer(customerNumber);
            if (customer == null) {
                logger.warn("Customer with id: " + customerNumber + " not found in database");
            } else {
                logger.warn("Customer with id: " + customerNumber + " found");
            }
        } catch (RuntimeException e) {
            logger.error("Exception occured while retrieveing customer: " + e.getLocalizedMessage());
        }
        return customer;
    }

    /**
     * This method take a customer details and add it to the database.
     * 
     * @param Customer that need to be added to the database
     * @return Returns the choice of the user
     */
    public String addCustomer(Customer customer) {
        String response = "";
        Customer temCustomer = null;
        try {

            // check the customer id is already there or not
            temCustomer = this.getCustomer(customer.getCustomerNumber());
            if (temCustomer == null) {
                response = customerRepo.addCustomer(customer);
            } else {
                response = "The customer with id " + customer.getCustomerNumber() + " Already exists. Addition failed";
            }


        } catch (RuntimeException e) {
            response = e.getLocalizedMessage();
            logger.error("Exception occured while adding customer: " + e.getLocalizedMessage());
        }
        return response;
    }

}


