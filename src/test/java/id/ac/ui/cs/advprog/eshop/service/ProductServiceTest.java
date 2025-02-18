package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Mockito.when(productRepository.create(product)).thenReturn(product);
        Product result = productService.create(product);

        assertNotNull(result);
        assertEquals("Sampo Cap Bambang", result.getProductName());
        assertEquals(100, result.getProductQuantity());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        product2.setProductName("Sampo Cap Bango");
        product2.setProductQuantity(200);
        List<Product> productList = Arrays.asList(product1, product2);
        Iterator<Product> productIterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);
        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        assertEquals("Sampo Cap Bambang", result.get(0).getProductName());
        assertEquals("Sampo Cap Bango", result.get(1).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void FindProductByID() {
        Product product = new Product();
        product.setProductID("abc-123-456shd");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.findById("abc-123-456shd")).thenReturn(product);

        Product result = productService.findById("abc-123-456shd");
        assertNotNull(result);
        assertEquals("Sampo Cap Bambang", result.getProductName());
        assertEquals("abc-123-456shd", result.getProductID());
        verify(productRepository, times(1)).findById(product.getProductID());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(200);
        when(productRepository.update(product)).thenReturn(product);
        productService.update(product);

        assertEquals("Sampo Cap Bango", product.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        when(productRepository.delete(product.getProductID())).thenReturn(true);
        productService.delete(product.getProductID());

        verify(productRepository, times(1)).delete(product.getProductID());
    }




}

