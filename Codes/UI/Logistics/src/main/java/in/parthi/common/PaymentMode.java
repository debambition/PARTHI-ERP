package in.parthi.common;

import java.util.Scanner;
import in.parthi.core.model.transaction.Transaction;

public enum PaymentMode {
    CASH, ONLINE;



    // Static method for user input
    public static void choose(Transaction transaction) {
        Scanner sc = Properties.getSacnnerInstance();
        int option;
        int i;
        do {
            i = 1;
            for (PaymentMode tmp : PaymentMode.values()) {
                System.out.println((i++) + "]\t" + tmp);

            }
            System.out.print("Choose your Option: ");
            option = sc.nextInt();

            if (option >= 1 && option <= PaymentMode.values().length) {
                transaction.setPaymentMode(PaymentMode.values()[option - 1].toString());
            } else {
                System.out.println("Please enter a valid option between 1-" + PaymentMode.values().length);

            }

        } while (option < 1 || option > PaymentMode.values().length);
    }
}
