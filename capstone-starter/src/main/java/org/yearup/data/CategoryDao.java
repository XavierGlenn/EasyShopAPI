package org.yearup.data;

import org.yearup.models.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategories();

    Category getById(int categoryId);

    Category getCategoryById(int categoryId);
    Category addCategory(Category category);
    Category updateCategory(int categoryId, Category category);
    Category deleteCategory(int categoryId);
}