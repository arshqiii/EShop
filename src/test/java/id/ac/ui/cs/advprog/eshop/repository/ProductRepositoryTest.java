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
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testCreateProductWithNullId(){
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        product.setId(null);

        Product createdProduct = productRepository.create(product);
        assertNotNull(createdProduct.getId());
    }

    @Test
    void testCreateProductWithEmptyId(){
        Product product = new Product();
        product.setId("");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        Product createdProduct = productRepository.create(product);
        assertNotNull(createdProduct.getId());
    }

    @Test
    void testCreateProductWithExistingId(){
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        product.setId("10002002010");

        Product createdProduct = productRepository.create(product);

        assertEquals(product.getId(), createdProduct.getId());
    }

    @Test
    void testCreateProductWithoutAnyFields() {
        Product product = new Product();
        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getId());
        assertNull(createdProduct.getName());
        assertEquals(0, createdProduct.getQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getId(), savedProduct.getId());
        savedProduct = productIterator.next();
        assertEquals(product2.getId(), savedProduct.getId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdIfExists() {
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(product.getId());
        assertNotNull(foundProduct.getId());
        assertEquals(product.getId(), foundProduct.getId());
    }
    @Test
    void testFindByIdIfNotExist(){
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        String idThatDoesntExist = "123ebd3d-239-460e-8880-71af6af63bd6";
        Product result = productRepository.findById(idThatDoesntExist);
        assertNull(result);
    }

    @Test
    void testUpdateProduct(){
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        product.setName("Sampo Cap Bango");
        product.setQuantity(1000);
        productRepository.update(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testDeleteProduct(){
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        assertNotNull(productRepository.findById(product.getId()));
        productRepository.delete(product.getId());
        assertNull(productRepository.findById(product.getId()));
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
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        Product productThatDoesntExist = productRepository.update(product);

        assertNull(productThatDoesntExist);
    }

}