package in.parthi.common;

import java.util.Scanner;
import in.parthi.core.model.transaction.Transaction;

public enum TransactionType {
    CREDIT, DEBIT;

    // Static method for user input
    public static void choose(Transaction transaction) {
        Scanner sc = Properties.getSacnnerInstance();
        int option;
        int i;
        do {
            i = 1;
            for (TransactionType tmp : TransactionType.values()) {
                System.out.println((i++) + "]\t" + tmp);

            }
            System.out.print("Choose your Option: ");
            option = sc.nextInt();

            if (option >= 1 && option <= TransactionType.values().length) {
                transaction.setTxnType(TransactionType.values()[option - 1].toString());
            } else {
                System.out.println("Please enter a valis option between 1-" + TransactionType.values().length);

            }

        } while (option < 1 || option > TransactionType.values().length);
    }
}
