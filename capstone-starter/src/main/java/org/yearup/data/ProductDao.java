package org.yearup.data;

import org.yearup.models.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {

    List<Product> search(int categoryId, BigDecimal minPrice, BigDecimal maxPrice, String color);
    List<Product> getProductsByCategoryId(int productId);
    Product getProductsById(int productId);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(int productId); }