package in.parthi.ui;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.common.Properties;
import in.parthi.common.TransactionCategory;
import in.parthi.core.model.product.AddProduct;
import in.parthi.core.model.product.Product;
import in.parthi.core.model.transaction.Transaction;
import in.parthi.core.service.ProductService;
import in.parthi.core.service.TransactionService;


public class ProductInterface {

    private static final Logger logger = LoggerFactory.getLogger(ProductInterface.class);

    ProductService productService = new ProductService();
    TransactionInterface transactionInterface = new TransactionInterface();
    TransactionService transactionService = new TransactionService();

    /**
     * This method take a product id and call repo class to retrieve the corresponding product.
     * 
     * @return Returns a Product matching with product ID
     */
    public Product getProduct() {

        Product product = null;
        Scanner sc = Properties.getSacnnerInstance();
        sc.nextLine();
        logger.info("Start taking product details from user");
        System.out.print("Enter Product ID: ");
        String id = sc.nextLine();
        product = productService.getProduct(id);
        return product;
    }

    /**
     * This method takes entry for a single product or multiple products after purchased from the vendor and Transactions details for that purchased added to the database.
     * 
     * @return Returns a response message for the addition of product/s added succesfully
     */
    public String addProducts() {
        Scanner sc = Properties.getSacnnerInstance();
        int numberOfProducts = 0;
        String productResponse = "";
        String txnResponse = "";
        String response = "";
        String hasMoreProduct = "N";

        AddProduct addProduct = new AddProduct();

        // Call the add transactions from transaction model
        Transaction transaction = new Transaction();
        transactionInterface.addTransaction(transaction);
        addProduct.setTransaction(transaction);
        System.out.println(txnResponse);
        // in for loop call the add product
        do {

            productResponse = addProduct();
            System.out.println(productResponse + "\nDo you want to add more product(s) [Y/N]");
            numberOfProducts++;
            // flush the extra enter
            sc.nextLine();
            hasMoreProduct = sc.nextLine();

        } while (hasMoreProduct.equalsIgnoreCase("Y"));

        response = productService.addProduct(addProduct);

        response = numberOfProducts + " products added successfully";

        return response;

    }

    /**
     * This method take a product details recently purchased product from vendor and to the database.
     * 
     * @return Returns a responce message for the addition done successfully.
     */
    public String addProduct(Product product) {
        String response = "";
        Scanner sc = Properties.getSacnnerInstance();
        sc.nextLine();
        logger.info("Start taking product details from user");

        try {
            // Take Stocking date from user
            LocalDate today = LocalDate.now();
            System.out.print("Enter Stockin Date e.g 2025-09-23 (default: " + today.toString() + "): ");
            String strDate = sc.nextLine();
            if (strDate.length() == 0) {
                product.setStockInDate(today);
            } else {
                product.setStockInDate(LocalDate.parse(strDate));
            }

            //
            product.setStatus(Properties.STATUS_AVAILABLE);

            //
            System.out.print("Enter Product ID: ");
            product.setId(sc.nextLine().toUpperCase());

            //
            System.out.print("Enter Product Category: ");
            product.setCategory(sc.nextLine().toUpperCase());

            //
            System.out.print("Enter Product Name: ");
            product.setName(sc.nextLine().toUpperCase());

            System.out.print("Enter Product Description: ");
            product.setDescription(sc.nextLine());

            System.out.print("Enter Product Cost: ");
            product.setCostPrice(sc.nextDouble());

            System.out.print("Enter Product mrp: ");
            product.setMrp(sc.nextDouble());

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            for (StackTraceElement str : e.getStackTrace()) {
                System.out.println(str.toString());
            }
            response = e.getLocalizedMessage();
        }
        return response;
    }

    /**
     * This method take a single product details and call service class to add the product to the db.
     * 
     * @return Returns a responce message for the addition action of product done successfully
     */
    public String addProduct() {
        String response = "";
        logger.info("Start taking product details from user");
        Scanner sc = Properties.getSacnnerInstance();
        sc.nextLine();

        boolean valid = false;
        boolean validName = false;
        boolean validMrp = false;
        String productName = "";
        String productCatagory = "";
        double mrpPrice = 0.0;
        logger.info("Start taking product details from user");

        LocalDate today = LocalDate.now();
        LocalDate inDate;

        Product product = new Product();
        double costPrice = 0.0;
        boolean value = false;
        try {
            // Take Stockin date from user
            do{
                System.out.print("Enter Stockin Date e.g 2025-09-23 (default: " + today.toString() + "): ");
                //sc.nextLine(); // to flush the extra enter
                String strDate = sc.nextLine();
                if (strDate.length() == 0) {
                    product.setStockInDate(today);
                    break;
                } else {
                    inDate = LocalDate.parse(strDate);
                    if(inDate.compareTo(today) <= 0){
                        product.setStockInDate(inDate);
                        break;
                    } else {
                        System.out.println("Please enter a valid date. Future date is not a valid");
                    }
                    
                }
            } while (true);
            
            //
            product.setStatus(Properties.STATUS_AVAILABLE);

            //
            
            boolean validId = false;

            while (!validId) {
                System.out.print("Enter Product ID (e.g ABC- or ABC-001): ");
                String input = sc.nextLine().toUpperCase();

                String newId;

                if (input.endsWith("-")) {
                    // User gave only prefix -> generate next ID
                    newId = productService.getNextProductId(input);
                } else {
                    // User gave full ID -> use directly
                    newId = input;
                }

                // Validate uniqueness
                if (productService.getProduct(newId) != null) {
                    System.out.println("Product ID already exists, please enter a different ID.");
                    logger.warn("Duplicate product ID detected: {}", newId);
                } else {
                    product.setId(newId);
                    System.out.println("Product Id registered as: " + product.getId());
                    validId = true;
                }
            }


            //
            System.out.print("Enter Product Category: ");
            while(!valid){
                try{
                    productCatagory = sc.nextLine().toUpperCase();

                    if(productCatagory.length() >= 2){
                        valid = true;
                        logger.info("Product Category entered: ");
                    }else{
                        System.out.print("Category cannot be blank. Please enter a valid category.\n");
                        logger.warn("Blank category input detected.");
                        System.out.print("Re-enter Product Category: ");
                    }

                }catch(RuntimeException e) {
                    System.out.print("Error reading input. Please try again.\n");
                    logger.error("Exception while reading product name: {}");
                    break;

                }
            }
            product.setCategory(productCatagory);
            //product.setCategory(sc.nextLine().toUpperCase());
            
            //
            System.out.print("Enter Product Name: ");
            while (!validName) {
                try {

                    productName = sc.nextLine(); // user input + remove extra spaces

                    if (productName.length() >= 2) {
                        validName = true;
                        logger.info("Product name entered:  ");
                    } else {
                        System.out.print("Product name can't be empty.\n");
                        logger.warn("Invalid product name length: ");
                        System.out.print("Please re-enter Product Name: ");  
                    }

                } catch (RuntimeException e) {
                    System.out.print("Error reading input. Please try again.\n");
                    //logger.error("Exception while reading product name: {}", e.getMessage());
                    break;
                }
            }
            product.setName(productName);

            System.out.print("Enter Product Description: ");
            product.setDescription(sc.nextLine());

        
            System.out.print("Enter Product Cost: ");
            while(!value){
                try{
                    costPrice = sc.nextDouble();
                    if(costPrice < 1){
                        System.out.print("Cost price must be greater 0. Please try again: ");
                        
                        logger.info("Cost price entered {}",costPrice);
                    } else {
                        value = true;
                    }
                    

                } catch (InputMismatchException e){
                    sc.nextLine(); // capture the wrong input
                    System.out.print("Invalid input, please enter a number or decimal value: ");
                    logger.warn("Invalid product cost input detected");
                    //break;
                }
            }
            product.setCostPrice(costPrice);
            

            System.out.print("Enter Product mrp: ");
             while(!validMrp){
                try{
                    mrpPrice = sc.nextDouble();
                    if(mrpPrice < 1){
                        System.out.print("Mrp can't be zero. Please try again: ");
                        logger.info("Mrp price entered {}",mrpPrice);
                    } else {
                        validMrp = true;
                    }
                    
                } catch (InputMismatchException e){
                     sc.nextLine(); // capture the wrong input
                    System.out.print("Invalid input, please enter a number or decimal value: ");
                    logger.warn("Invalid product mrp input detected");
                }
            }       
            product.setMrp(mrpPrice);

            response = productService.addProduct(product);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            for (StackTraceElement str : e.getStackTrace()) {
                System.out.println(str.toString());
            }
            response = e.getLocalizedMessage();
        }
        return response;
    }

    public String returnTovendor() {
        String response = null;
        Scanner sc = Properties.getSacnnerInstance();
        // Adding transaction details before updateing the product status
        transactionInterface.addTransaction(TransactionCategory.PRODUCT_COST.toString());
        sc.nextLine();
        logger.info("Taking product id from the user");
        System.out.print("Please enter product id: ");
        String id = sc.nextLine();
        response = productService.returnToVendor(id);

        return response;

    }
}


