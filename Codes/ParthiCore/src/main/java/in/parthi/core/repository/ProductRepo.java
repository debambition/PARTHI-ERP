package in.parthi.core.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import in.parthi.common.Properties;
import in.parthi.core.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ProductRepo {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepo.class);

    /**
     * This method take a an procuct id and retrieve the product from the database.
     * 
     * @param String id with which the product needs to be found
     * @return Returns the product
     * @throws RuntimeException if the product is unavailable in the database.
     */
    public Product getProduct(String id) {// Create a NotFound Exception
        Product product = null;
        EntityManager entityManager = Properties.getDBConnection();
        product = entityManager.find(Product.class, id);

        return product;

    }

    /**
     * This method take a product details and add it to the database.
     * 
     * @param Product that need to be added to the database
     * @return Returns the choice of the user
     * @throws RuntimeException if the product is already available in the database.
     */
    public String addProduct(Product product) throws RuntimeException {// Create a NotFound Exception
        //
        logger.info("Adding product with ID " + product.getId());
        EntityManager entityManager = Properties.getDBConnection();
        entityManager.getTransaction().begin();
        String response = "";

        // add product and save to db
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        response = "Product added successfully";
        logger.info("Product with id: " + product.getId() + " added to the database");
        return response;
    }

    /**
     * This method take a product id and check with the database.
     * 
     * @param Productid that needs to be checked to the database
     * @return Returns responce
     * @throws RuntimeException if the product is not available in the database.
     */
    public String updateProduct(Product product) throws RuntimeException {
        logger.info("Updating product with ID " + product.getId());
        //
        EntityManager entityManager = Properties.getDBConnection();
        entityManager.getTransaction().begin();
        String response = "";
        // product = entityManager.find(Product.class, id);

        // product and save to db
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        response = "Product with id: " + product.getId() + " updated in the database";
        logger.info(response);

        return response;

    }

    /**
     * This method take a procuct id prefix and retrieve the next product id from the database.
     * 
     * @param String id prefix with which the product needs to be found
     * @return Returns the largest product id
     * @throws RuntimeException if the product is unavailable in the database.
     */
    public String getNextProductId(String prefix) {
        logger.info("fetching next product ID with series  " + prefix);
        String maxId = null;
        EntityManager entityManager = Properties.getDBConnection();
        TypedQuery<String> query = entityManager.createQuery("SELECT MAX(p.id) FROM Product p WHERE p.id LIKE :prefix", String.class);
        query.setParameter("prefix", prefix + "%");

        maxId = query.getSingleResult();
        logger.info("Largest Product ID: " + maxId);
        ///

        return maxId;
    }


}
