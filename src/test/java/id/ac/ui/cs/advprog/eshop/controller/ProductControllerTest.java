package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Test");
        product.setProductQuantity(10);

        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testUpdateProductPage() throws Exception {
        Product product = new Product();
        product.setProductID(UUID.randomUUID().toString());
        when(productService.findById(product.getProductID())).thenReturn(product);
        mockMvc.perform(get("/product/edit/" + product.getProductID()))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testUpdateProductPage_NotFound() throws Exception {
        Product product = new Product();
        product.setProductID(UUID.randomUUID().toString());
        when(productService.findById(product.getProductID())).thenReturn(null);
        mockMvc.perform(get("/product/edit/" + product.getProductID()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
        verify(productService, times(1)).findById(product.getProductID());
    }

    @Test
    void testUpdateProduct() throws Exception {
        Product product = new Product();
        product.setProductID(UUID.randomUUID().toString());
        product.setProductName("Test");
        product.setProductQuantity(10);

       when(productService.update(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/product/edit")
                .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).update(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        String productID = UUID.randomUUID().toString();
        when(productService.delete(productID)).thenReturn(true);

        mockMvc.perform(get("/product/delete/" + productID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
        verify(productService, times(1)).delete(productID);
    }

    @Test
    void testListProductPage() throws Exception {
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));
    }
}
