package in.parthi.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.core.model.voucher.Voucher;
import in.parthi.core.repository.VoucherRepo;

public class VoucherService {
    private static final Logger logger = LoggerFactory.getLogger(VoucherService.class);

    VoucherRepo voucherRepo = new VoucherRepo();


/**
     * This method take a Transaction details and add it to the database.
     * 
     * @param Transaction that need to be added to the database
     * @return Returns the Responce msg of the user
     */

 public String addVoucher(Voucher voucher) {
        String response = "";
        try {
            response = voucherRepo.addVoucher(voucher);
        } catch (RuntimeException e) {
            response = e.getLocalizedMessage();
            logger.error("Exception occured while adding Transaction: " + e.getLocalizedMessage());
        }
        return response;
    }
    
}
