package org.yearup.data.azure;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;
import org.yearup.models.Product;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class AzureCategoryDao extends AzureDaoBase implements CategoryDao {

    public AzureCategoryDao(DataSource dataSource) {
        super(dataSource); }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Category category = new Category();
                category.setCategory_id(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name")); //FIXME tf goes here?
                categories.add(category); }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching categories", e); }
        return categories; }

    @Override
    public List<Product> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String color) {
        return List.of(); }

    @Override
    public List<Product> getCategory_Id(int category_id) {
        return List.of(); }

    @Override
    public Category create(Category category) {
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, category.getName()); //FIXME tf goes here?
            statement.setString(2, category.getDescription()); //FIXME tf goes here?

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        category.setCategory_id(generatedKeys.getInt(1));
                    }
                }
            } else {
                throw new RuntimeException("Failed to insert category");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            throw new RuntimeException("Error creating category", e); }
        return category; }

    @Override
    public Category getById(int categoryId) {
        String sql = "SELECT * FROM categories WHERE category_id = ?"; // Adjust column name as needed

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Category category = new Category();
                    category.setCategory_id(resultSet.getInt("category_id")); //FIXME tf goes here?
                    category.setName(resultSet.getString("name")); //FIXME tf goes here?
                    category.setDescription(resultSet.getString("description")); //FIXME tf goes here?
                    return category; }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching category by ID", e); }
        return null; }

    @Override
    public void update(int categoryId, Category category) {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category.getName()); //FIXME tf goes here?
            statement.setString(2, category.getDescription()); //FIXME tf goes here?
            statement.setInt(3, categoryId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            throw new RuntimeException("Error updating category", e); }
    }

    @Override
    public Category create(Product product) {
        return null; }

    @Override
    public void update(int productId, Product product) {}

    @Override
    public void delete(int categoryId) {
        String sql = "DELETE FROM categories WHERE category_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            throw new RuntimeException("Error deleting category", e);
        }
    }
}