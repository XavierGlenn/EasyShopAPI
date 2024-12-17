package org.yearup.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<Integer, ShoppingCartItem> items = new HashMap<>();

    public Map<Integer, ShoppingCartItem> getItems()
    { return items; }

    public void setItems(Map<Integer, ShoppingCartItem> items)
    { this.items = items; }

    public boolean contains(int productId)
    { return items.containsKey(productId); }

    public void addItems(Map<Integer, ShoppingCartItem> items)
    { this.items = items; }

    public ShoppingCartItem get(int productId)
    { return items.get(productId); }

    public BigDecimal getTotal() {
        return items.values().stream().map(i -> i.getLineTotal()).reduce( BigDecimal.ZERO, (lineTotal, subTotal) -> subTotal.add(lineTotal)); }

    public void updateItems(int productId, int quantity) {}
    public void deleteItems(int productId, int quantity) {}
}
