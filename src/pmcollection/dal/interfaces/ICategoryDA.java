package pmcollection.dal.interfaces;

import pmcollection.be.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryDA {
    Category createCategory(Category category) throws SQLException;
    List<Category> getAllCategories();
    Category getCategory(int id);
    void updateCategory(Category category);
    void deleteCategory(Category category);
}
