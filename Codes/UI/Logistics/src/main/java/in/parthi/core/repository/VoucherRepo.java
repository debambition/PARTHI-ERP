package in.parthi.core.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.parthi.core.model.voucher.Voucher;

public class VoucherRepo {
    private static final Logger logger = LoggerFactory.getLogger(VoucherRepo.class);
    List<Voucher> voucherList = new ArrayList<>();

    /**
     * This method take a voucher details and add it to the database.
     * 
     * @param Vouher that need to be added to the database
     * @return Returns the choice of the user
     * @throws RuntimeException if the product is already available in the database.
     */

public String addVoucher(Voucher voucher) throws RuntimeException{
    String response = "";
    boolean hasVoucher = false;
    for (Voucher tempVoucher : voucherList)
    {
        if (tempVoucher.getId().equalsIgnoreCase(voucher.getId()))
        {
            hasVoucher = true;
            break;
        }
    }

    if(hasVoucher)
    {
        logger.warn("Voucher with id: " + voucher.getId() + " already available in database");
        throw new RuntimeException("The Voucher with id " + voucher.getId()
                    + " Already present in database, Unable to create the transaction");

    }  else {
           voucherList.add(voucher);
            response = "Voucher with id: " + voucher.getId() + " added successfully";
            logger.info("Voucher with id: " + voucher.getId() + " added successfully");
        } 

    return response;
}
}
