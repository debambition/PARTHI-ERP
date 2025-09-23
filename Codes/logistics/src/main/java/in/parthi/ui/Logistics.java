package in.parthi.ui;

import java.io.IOException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.common.Properties;
import in.parthi.core.model.product.Product;
import in.parthi.core.model.transaction.Transaction;

public class Logistics {

    private static final Logger logger = LoggerFactory.getLogger(Logistics.class);


    public static void main(String[] args) {

        // Clear screen
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }


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
                    response = productInterface.addProducts();
                    System.out.println("=============================================================");
                    System.out.println(response);
                    System.out.println("=============================================================\n\n");
                    break;
                case 2:
                    System.out.println("============Return Product==========");

                    System.out.println(productInterface.returnTovendor());

                    break;

                case 3:
                    System.out.println("============Adding Transaction==========");
                    response = (transactionInterface.addTransaction(""));
                    System.out.println("=============================================================");
                    System.out.println(response);
                    System.out.println("=============================================================\n\n");;
                    break;

                case 4:
                    System.out.println("============Adding Voucher==========");
                    // System.out.println(VoucherInterface.addVoucher());

                    break;
                case 5:
                    sc.close();

                    System.out.println("Exiting Application Gracefully");
                    System.exit(0);
                default:
                    break;
            }

        } while (option >= 1 && option < 5);


    }


    /**
     * This method returns the option provided by the user
     *
     * @return Returns the choice of the user
     */
    private int getOption() {
        System.out.println("==========MENU========");
        System.out.println("1] Add product(s)");
        System.out.println("2] Return product");
        System.out.println("3] Add Transaction");
        System.out.println("4] Add Voucher");
        System.out.println("5] Exit the App");
        System.out.print("Enter your option number(1 - 5): ");
        int choice = -9999;
        try {
            do {
                if (choice != -9999) {
                    System.out.print("Please select a valid choice between 1 - 5: ");
                }
                Scanner sc = Properties.getSacnnerInstance();
                choice = sc.nextInt();
            } while (choice < 1 || choice > 5);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            logger.warn(e.getMessage());
        } finally {
            // getChoice.close();
        }
        return choice;
    }
}
