package in.parthi.core.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.parthi.common.Properties;
import in.parthi.core.model.product.AddProduct;
import in.parthi.core.model.product.Product;
import in.parthi.core.repository.ProductRepo;

public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    ProductRepo productRepo = new ProductRepo();
    TransactionService transactionService = new TransactionService();

    /**
     * This method take a an procuct id and retrieve the product from the database.
     * 
     * @param String id with which the product needs to be found
     * @return Returns the product
     * @throws RuntimeException if the product is unavailable in the database.
     */
    public Product getProduct(String id) {
        Product product = null;
        try {
            product = productRepo.getProduct(id);
            if (product == null) {
                logger.warn("Product with id: " + id + " not found in database");
            }
        } catch (RuntimeException e) {
            logger.error("Exception occured while adding product: " + e.getLocalizedMessage());
        }
        return product;
    }

    /**
     * This method take a product details and add it to the database.
     * 
     * @param Product that need to be added to the database
     * @return Returns the choice of the user
     */
    public String addProduct(Product product) {
        String response = "";
        Product temProduct = null;
        try {

            // check the product id is already there or not
            temProduct = this.getProduct(product.getId());
            if (temProduct == null) {
                response = productRepo.addProduct(product);
            } else {
                response = "The product with id " + product.getId() + " Already exists. Addition failed";
            }


        } catch (RuntimeException e) {
            response = e.getLocalizedMessage();
            logger.error("Exception occured while adding product: " + e.getLocalizedMessage());
        }
        return response;
    }

    /**
     * This method take a product details and add it to the database.
     * 
     * @param Product that need to be added to the database
     * @return Returns the choice of the user
     */
    public String addProduct(AddProduct addProduct) {
        String response = "";
        try {
            response = transactionService.addTransaction(addProduct.getTransaction());
            if (response.contains("added successfully")) {
                for (Product product : addProduct.getProduct()) {
                    response = productRepo.addProduct(product);
                }

            }

        } catch (RuntimeException e) {
            response = e.getLocalizedMessage();
            logger.error("Exception occured while adding product: " + e.getLocalizedMessage());
        }
        return response;
    }

    /**
     * This method take a product details and remove product from the database.
     * 
     * @param id that need to be delete to the database
     * @return Returns the responce
     */
    public String returnToVendor(String id) {
        String response = "";

        try {
            // Fetch product
            Product product = productRepo.getProduct(id);

            if (product != null) {
                // Apply business logic here
                product.setStatus(Properties.STATUS_RETURNED);
                product.setStockOutDate(LocalDate.now());

                response = productRepo.returnToVendor(product);


                response = "Product with id " + id + " updated successfully";
                logger.info(response);
            } else {
                response = "Product with id " + id + " not found";
                logger.warn(response);
            }

        } catch (RuntimeException e) {
            response = e.getLocalizedMessage();
            logger.error("Exception in returnToVendor: " + response);
        }

        return response;
    }


    public String getNextProductId(String prefixID){
        String nextId = "";
        String maxId = "";
        int num = 0;
        try {
            maxId = productRepo.getNextProductId(prefixID);
            if (maxId == null || maxId.length() == 0) {
                nextId = prefixID+"001";
            }else{
                maxId = maxId.split("-")[1];
                num = Integer.parseInt(maxId) + 1;
                 nextId = String.format("%s%03d", prefixID, num);
                //nextId = prefixID + "00" +(++num);
            }
        } catch (RuntimeException e) {
            logger.error("Exception occured while adding product: " + e.getLocalizedMessage());
        }
        return nextId;
    }
}


