package pmcollection.dal;

import pmcollection.be.Category;

import java.util.List;

public interface ICategoryDA {
    int createCategory(Category category);
    List<Category> getAllCategories();
    Category getCategory(int id);
    void updateCategory(Category category);
    void deleteCategory(Category category);
}
