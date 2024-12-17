package org.yearup.data;

import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCartDao {
    ShoppingCart getCartByUserId(int userId);
    List<Product> ShoppingCart(int categoryId, int productId, BigDecimal price, String color);;
    void deleteFromCart(int category_id);
    void updateFromCart(); }