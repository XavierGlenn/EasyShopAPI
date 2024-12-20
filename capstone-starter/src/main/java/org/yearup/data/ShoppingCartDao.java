package org.yearup.data;

import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao {
    ShoppingCart getByUserId(int userId);
    void addProduct(int userId, Product product);
    void update(int userId, ShoppingCartItem item);
    void delete(int userId); }