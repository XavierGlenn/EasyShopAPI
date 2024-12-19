package org.yearup.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Integer, ShoppingCartItem> items = new HashMap<>();

    public Map<Integer, ShoppingCartItem> getItems() {
        return new HashMap<>(items); // Defensive copy to prevent external modifications
    }

    public void setItems(Map<Integer, ShoppingCartItem> items) {
        this.items = new HashMap<>(items); // Defensive copy
    }

    public boolean contains(int productId) {
        return items.containsKey(productId);
    }

    public void add(ShoppingCartItem item) {
        int productId = item.getProductId();
        if (contains(productId)) {
            ShoppingCartItem existingItem = items.get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            items.put(productId, item);
        }
    }

    public void remove(int productId) {
        items.remove(productId);
    }

    public ShoppingCartItem get(int productId) {
        return items.get(productId);
    }

    public void clear() {
        items.clear();
    }

    public BigDecimal getTotal() {
        return items.values()
                .stream()
                .map(ShoppingCartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}