package org.yearup.data.azure;

import org.springframework.stereotype.Component;
import org.yearup.models.Category;
import org.yearup.models.Product;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

@Component
public abstract class AzureCategoryDao extends AzureDaoBase {

    public AzureCategoryDao(DataSource dataSource) {
        super(dataSource); }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT category_id, name, description FROM categories";
        List<Category> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                categories.add(mapRow(resultSet)); }

        } catch (SQLException e) {
            Logger.error("Error fetching categories from database", e);
            throw new RuntimeException("Error fetching categories from database", e); }
        return categories;
    }

    @Override
    public Product getById(int categoryId) { // Simulate database retrieval
        return database.find(Category.class, categoryId); }

    @Override
    public Category create(Category category) {
        // Add the new category to the database
        database.save(category);
        return category; }

    @Override
    public void update(int categoryId, Category category) throws SQLException {
        Category existingCategory = getById(categoryId);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            database.update(existingCategory);
        } else {
            throw new SQLException("Category not found."); }
    }

    @Override
    public void delete(int categoryId) throws SQLException {
        Category category = getById(categoryId);
        if (category != null) {
            database.delete(category);
        } else {
            throw new SQLException("Category not found."); }
    }

    private Category mapRow(ResultSet row) throws SQLException {
        Category category = new Category();
        category.setCategory_id(row.getInt("category_id"));
        category.setName(row.getString("name"));
        category.setDescription(row.getString("description"));
        return category; }
}
