package in.parthi.core.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.parthi.core.model.product.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Logistic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        product = entityManager.find(Product.class, id);

        entityManager.close();
        entityManagerFactory.close();

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
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Logistic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String response = "";

        // add product and save to db
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        response = "Product added successfully";
        logger.info("Product with id: " + product.getId() + " added to the database");
        entityManager.close();
        entityManagerFactory.close();
        return response;
    }

    /**
     * This method take a product id and check with the database.
     * 
     * @param Productid that needs to be checked to the database
     * @return Returns responce
     * @throws RuntimeException if the product is not available in the database.
     */
    public String returnToVendor(Product product) throws RuntimeException {

        //
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Logistic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String response = "";
        // product = entityManager.find(Product.class, id);

        // product and save to db
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        response = "Product added successfully";
        logger.info("Product with id: " + product.getId() + " added to the database");
        entityManager.close();
        entityManagerFactory.close();

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
        String maxId = null;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Logistic");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<String> query = entityManager.createQuery("SELECT MAX(p.id) FROM Product p WHERE p.id LIKE :prefix", String.class);
        query.setParameter("prefix", prefix + "%");

        maxId = query.getSingleResult();
        logger.info("Largest Product ID: " + maxId);
        ///

        entityManager.close();
        entityManagerFactory.close();

        return maxId;
    }


}
