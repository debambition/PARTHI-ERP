package in.parthi.core.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.parthi.common.Properties;
import in.parthi.common.TransactionCategory;
import in.parthi.common.TransactionType;
import in.parthi.core.model.product.AddProduct;
import in.parthi.core.model.product.Product;
import in.parthi.core.model.transaction.Transaction;
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
            Product tmpProduct;
            if (response.contains("added successfully")) {
                for (Product product : addProduct.getProducts()) {
                    tmpProduct = productRepo.getProduct(product.getId());
                    if (tmpProduct != null){
                        product.setId(productRepo.getNextProductId(product.getId().split("-")[0]));
                    }
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
     * @return Returns the response
     */
    public String returnToVendor(String id, LocalDate stockOutDate, String paymentmode) {
        String response = "";

        try {
            // Fetch product
            Product product = productRepo.getProduct(id);

            if (product != null) {
                // Apply business logic here
                product.setStatus(Properties.STATUS_RETURNED);
                product.setStockOutDate(stockOutDate);

                response = productRepo.returnToVendor(product);
                
                Transaction transaction = new Transaction();
                transaction.setAmount(product.getCostPrice());
                transaction.setDescription("Product with id "+product.getId()+" returned to vendor");
                transaction.setParticular(product.getId());
                transaction.setTxnCategory(TransactionCategory.PRODUCT_RETURN.toString());
                transaction.setTxnType(TransactionType.CREDIT.toString());
                transaction.setPaymentMode(paymentmode);
                transaction.setTransactionDate(stockOutDate);
                
                response = response +"\n"+transactionService.addTransaction(transaction);

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


