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
        for (int i = 0; i < productData.size(); i++){
            if (productData.get(i).getProductID().equals(updatedProduct.getProductID())){
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    public void delete(String id) {
        for (int i = 0; i < productData.size(); i++){
            if (productData.get(i).getProductID().equals(id)){
                productData.remove(i);
                return;
            }
        }
    }
}