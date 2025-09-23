package in.parthi.ui;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.common.Properties;
import in.parthi.core.model.product.Product;
import in.parthi.core.model.transaction.Transaction;

public class Logistics {

    private static final Logger logger = LoggerFactory.getLogger(Logistics.class);


    public static void main(String[] args) {


        Logistics logistics = new Logistics();
        ProductInterface productInterface = new ProductInterface();
        TransactionInterface transactionInterface = new TransactionInterface();
        Scanner sc = Properties.getSacnnerInstance();
        String response = "";
        int option = 0;
        do {
            option = logistics.getOption();

            switch (option) {
                case 1:
                    System.out.println("============Adding Product==========");
                    response = productInterface.addProduct();
                    System.out.println("=============================================================");
                    System.out.println(response);
                    System.out.println("=============================================================\n\n");
                    break;
                case 2:
                    System.out.println("============Adding Products(Vendor)==========");
                    System.out.println(productInterface.addProducts());
                    break;

                case 3:
                    System.out.println("============Get Product==========");
                    Product product = productInterface.getProduct();
                    System.out.println(product.toString());
                    break;
                case 4:
                    System.out.println("============Return Product==========");

                    System.out.println(productInterface.returnTovendor());

                    break;

                case 5:
                    System.out.println("============Adding Transaction==========");
                    response = (transactionInterface.addTransaction(""));
                    System.out.println("=============================================================");
                    System.out.println(response);
                    System.out.println("=============================================================\n\n");;
                    break;

                case 6:
                    System.out.println("============Get Transaction==========");
                    Transaction transaction = transactionInterface.gettransaction();
                    System.out.println(transaction.toString());
                    break;
                case 7:
                    System.out.println("============Adding Voucher==========");
                    // System.out.println(VoucherInterface.addVoucher());

                    break;
                case 8:
                    sc.close();

                    System.out.println("Exiting Application Gracefully");
                    System.exit(0);
                default:
                    break;
            }

        } while (option >= 1 && option < 8);


    }


    /**
     * This method returns the option provided by the user
     *
     * @return Returns the choice of the user
     */
    private int getOption() {
        System.out.println("==========MENU========");
        System.out.println("1] Add product");
        System.out.println("2] Add products(Vendor)");
        System.out.println("3] Get Product");
        System.out.println("4] Return product");
        System.out.println("5] Add Transaction");
        System.out.println("6] Get Transaction");
        System.out.println("7] Add Voucher");
        System.out.println("8] Exit the App");
        System.out.print("Enter your option number(1 - 8): ");
        int choice = -9999;
        try {
            do {
                if (choice != -9999) {
                    System.out.print("Please select a valid choice between 1 - 8: ");
                }
                Scanner sc = Properties.getSacnnerInstance();
                choice = sc.nextInt();
            } while (choice < 1 || choice > 8);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            logger.warn(e.getMessage());
        } finally {
            // getChoice.close();
        }
        return choice;
    }
}
