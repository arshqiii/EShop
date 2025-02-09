package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductID() == null || product.getProductID().isEmpty()) {
            product.setProductID(UUID.randomUUID().toString()); // Generate ID unik
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        for (Product product : productData){
            if (product.getProductID().equals(id)){
                return product;
            }
        }
        return null;
    }

    public Product update(Product updatedProduct) {
        Product productToEdit = findById(updatedProduct.getProductID());
        if (productToEdit != null) {
            productToEdit.setProductName(updatedProduct.getProductName());
            productToEdit.setProductQuantity(updatedProduct.getProductQuantity());
        }
        return productToEdit;
    }

    public void delete(String id) {
        Product deletedProduct = findById(id);
        if (deletedProduct != null) {
            productData.remove(deletedProduct);
        }
    }
}