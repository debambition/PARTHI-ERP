/**
 * This is a product class that provide the defination of the product and provides all the method
 * thats required to maintain the products.
 *
 * @author Dibakar Deb
 * @since 2025-08-30
 */

package in.parthi.core.model.product;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
    @Id
    private String id;
    private String category;
    private String name;
    private String description;
    private double costPrice;
    private double sellingPrice;
    private double mrp;
    String status; // TODO Create a enum for the status with AVAILABLE, SOLDOUT, RETURNED
    LocalDate stockInDate;
    LocalDate stockOutDate;


    public Product() {}


    public Product(String id, String category, String name, String description, double costPrice, double sellingPrice, double mrp, String status, LocalDate stockInDate, LocalDate stockOutDate) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.mrp = mrp;
        this.status = status;
        this.stockInDate = stockInDate;
        this.stockOutDate = stockOutDate;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return this.sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getMrp() {
        return this.mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStockInDate() {
        return this.stockInDate;
    }

    public void setStockInDate(LocalDate stockInDate) {
        this.stockInDate = stockInDate;
    }

    public LocalDate getStockOutDate() {
        return this.stockOutDate;
    }

    public void setStockOutDate(LocalDate stockOutDate) {
        this.stockOutDate = stockOutDate;
    }

    public String toString() {
        return "ID: " + this.getId() + "\n" + "Category: " + this.getCategory() + "\n" + "Name: " + this.getName() + "\n" + "Description: " + this.getDescription() + "\n" + "MRP: " + this.getMrp()
                + "\n" + "Cost Price: " + this.getCostPrice() + "\n" + "Status: " + this.getStatus() + "\n" + "Check In Date: " + this.getStockInDate() + "\n" + "Check Out Date: "
                + this.getStockOutDate();
    }

}
