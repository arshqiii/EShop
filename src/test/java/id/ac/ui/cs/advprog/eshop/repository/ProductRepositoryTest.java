package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    @Test
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductID(), savedProduct.getProductID());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testCreateProductWithNullId(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        product.setProductID(null);

        Product createdProduct = productRepository.create(product);
        assertNotNull(createdProduct.getProductID());
    }

    @Test
    void testCreateProductWithEmptyId(){
        Product product = new Product();
        product.setProductID("");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Product createdProduct = productRepository.create(product);
        assertNotNull(createdProduct.getProductID());
    }

    @Test
    void testCreateProductWithExistingId(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        product.setProductID("10002002010");

        Product createdProduct = productRepository.create(product);

        assertEquals(product.getProductID(), createdProduct.getProductID());
    }

    @Test
    void testCreateProductWithoutAnyFields() {
        Product product = new Product();
        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductID());
        assertNull(createdProduct.getProductName());
        assertEquals(0, createdProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductID("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductID(), savedProduct.getProductID());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductID(), savedProduct.getProductID());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdIfExists() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(product.getProductID());
        assertNotNull(foundProduct.getProductID());
        assertEquals(product.getProductID(), foundProduct.getProductID());
    }
    @Test
    void testFindByIdIfNotExist(){
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        String idThatDoesntExist = "123ebd3d-239-460e-8880-71af6af63bd6";
        Product result = productRepository.findById(idThatDoesntExist);
        assertNull(result);
    }

    @Test
    void testUpdateProduct(){
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(1000);
        productRepository.update(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductID(), savedProduct.getProductID());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct(){
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNotNull(productRepository.findById(product.getProductID()));
        productRepository.delete(product.getProductID());
        assertNull(productRepository.findById(product.getProductID()));
    }

    @Test
    void testDeleteProductIfNotExist(){
        String idThatDoesntExist = "123ebd3d-239-460e-8880-71af6af63bd6";
        assertNull(productRepository.findById(idThatDoesntExist));
        boolean deleted = productRepository.delete(idThatDoesntExist);
        assertFalse(deleted);
    }

    @Test
    void testEditProductIfNotExist(){
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Product productThatDoesntExist = productRepository.update(product);

        assertNull(productThatDoesntExist);
    }

}