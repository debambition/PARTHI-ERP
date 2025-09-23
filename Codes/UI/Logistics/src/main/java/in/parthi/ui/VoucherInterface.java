package in.parthi.ui;

import java.time.LocalDate;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.parthi.common.Properties;
import in.parthi.core.model.voucher.Voucher;
import in.parthi.core.service.VoucherService;

public class VoucherInterface{
    private static final Logger logger = LoggerFactory.getLogger(VoucherInterface.class);
    VoucherService voucherService = new VoucherService();

    /**
     * This method take a voucher detail and call service class to add the voucher.
     * 
     * @return Returns a responce message for the addition action of voucher
     */

     public String addVoucher()
     {
        String response = "";
        logger.info("Start taking voucher details from user");
         Scanner sc = Properties.getSacnnerInstance();
        sc.nextLine();
        Voucher voucher = new Voucher();
        try{
            // Take voucher details from user
            LocalDate today = LocalDate.now();
             System.out.print("Enter Start date e.g 2025-09-23 (default: " + today.toString() + "): ");
             String strDate = sc.nextLine();
             if(strDate.length() == 0){
                voucher.setStartDate(today);
             } else {
                voucher.setStartDate(LocalDate.parse(strDate));
             }

              System.out.print("Enter Invoice Id: ");
              voucher.setId(sc.nextLine());

              System.out.print("Enter Amount: ");
              voucher.setAmount(sc.nextDouble());

              System.out.println("Expiry date is 6 months from the start date");
              LocalDate expiryDate = voucher.getStartDate().plusMonths(6);
              voucher.setExpiryDate(expiryDate);

            response = voucherService.addVoucher(voucher);

        } catch (RuntimeException e){
            response = e.getLocalizedMessage();
            logger.error("Exception occured while adding a voucher"+ e.getLocalizedMessage());
        }

        return response;
       

     }
}