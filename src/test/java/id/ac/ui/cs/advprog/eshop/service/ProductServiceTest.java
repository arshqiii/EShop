package id.ac.ui.cs.advprog.eshop.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        Mockito.when(productRepository.create(product)).thenReturn(product);
        Product result = productService.create(product);

        assertNotNull(result);
        assertEquals("Sampo Cap Bambang", result.getName());
        assertEquals(100, result.getQuantity());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        product2.setName("Sampo Cap Bango");
        product2.setQuantity(200);
        List<Product> productList = Arrays.asList(product1, product2);
        Iterator<Product> productIterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);
        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        assertEquals("Sampo Cap Bambang", result.get(0).getName());
        assertEquals("Sampo Cap Bango", result.get(1).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void FindProductByID() {
        Product product = new Product();
        product.setId("abc-123-456shd");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        when(productRepository.findById("abc-123-456shd")).thenReturn(product);

        Product result = productService.findById("abc-123-456shd");
        assertNotNull(result);
        assertEquals("Sampo Cap Bambang", result.getName());
        assertEquals("abc-123-456shd", result.getId());
        verify(productRepository, times(1)).findById(product.getId());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        product.setName("Sampo Cap Bango");
        product.setQuantity(200);
        when(productRepository.update(product)).thenReturn(product);
        productService.update(product);

        assertEquals("Sampo Cap Bango", product.getName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        when(productRepository.delete(product.getId())).thenReturn(true);
        productService.delete(product.getId());

        verify(productRepository, times(1)).delete(product.getId());
    }




}

