

import java.math.BigDecimal;

import org.hibernate.Session;

import com.hibernate.persistance.HibernatePersistence;
import com.hibernate.tutorial.Category;
import com.hibernate.tutorial.Product;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

/**
 * Unit test for simple App.
 */
public class MainTest 
    extends TestCase
{
	private static Session session;
    private static Category category;
    private static Product product;

    
    @org.junit.Test
    public void testCategoryInsertion(){
        String name = "Penalty";
        category = new Category();
        session = HibernatePersistence.getSessionFactory().openSession();
        category.setName(name);
        session.beginTransaction();
        
        Integer categoryId = (Integer) session.save(category);
        session.getTransaction().commit();
        category = (Category) session.get(Category.class, categoryId);
        
        assertEquals("Penalty", category.getName());
    }
    
    @org.junit.Test
    public void testProductInsertion(){
        product = new Product();
        session = HibernatePersistence.getSessionFactory().openSession();
        product.setName("COKE CREATED");
        product.setCode("C002");
        product.setPrice(new BigDecimal("18.00"));
        String[] namesArray = {"leo", "julito", "esteban", "andres", "david"};
        product.setTags(namesArray);
        product.setCategory(category);
        session.beginTransaction();


        Integer productId =(Integer) session.save(product);
        session.getTransaction().commit();
        product = (Product) session.get(Product.class, productId);
        //asserts
        Assert.assertArrayEquals(namesArray, product.getTags());
    }

    @org.junit.Test
    public void testProductCreateUpdate(){
        product = new Product();
        session = HibernatePersistence.getSessionFactory().openSession();
        product.setName("COKE CREATED/UPDATED");
        product.setCode("C002");
        product.setPrice(new BigDecimal("18.00"));
        //arrays
        String[] namesArray = {"leo", "julito", "esteban", "andres", "david"};
        String[] lastNamesArray = {"chen", "nunez", "guevara", "arcia", "mulgrave"};

        product.setTags(namesArray);
        product.setCategory(category);
        session.beginTransaction();


        Integer productId =(Integer) session.save(product);
        session.getTransaction().commit();
        product = (Product) session.get(Product.class, productId);
        //assert
        Assert.assertArrayEquals(namesArray, product.getTags());


        //UPDATE
        session.beginTransaction();
        product.setTags(lastNamesArray);
        session.save(product);
        session.getTransaction().commit();
        //GET AGAIN UPDATED PRODUCT
        product = (Product) session.get(Product.class, productId);
        //assert
        Assert.assertArrayEquals(lastNamesArray, product.getTags());
    }
}
