package org.yearup.data.azure;

import org.yearup.models.Category;
import org.yearup.models.Product;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AzureDaoBase
{
    public DataSource dataSource;

    public AzureDaoBase(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }

    public abstract List<Category> getAllCategories();

    public abstract List<Product> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String color);

    public abstract List<Product> getCategory_Id(int category_id);

    public abstract Category getById(int categoryId);

    public abstract Category create(Category category);

    public abstract void update(int categoryId, Category category) throws SQLException;

    public abstract Category create(Product product);

    public abstract void update(int productId, Product product);

    public abstract void delete(int categoryId) throws SQLException;
}
