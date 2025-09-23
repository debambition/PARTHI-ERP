package in.parthi.core.model.product;

import java.util.List;
import in.parthi.core.model.transaction.Transaction;

public class AddProduct {
    private Transaction transaction;
    private List<Product> products;
    

    public AddProduct() {
    }

    public AddProduct(Transaction transaction, List<Product> products) {
        this.transaction = transaction;
        this.products = products;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProduct(List<Product> products) {
        this.products = products;
    }

}
