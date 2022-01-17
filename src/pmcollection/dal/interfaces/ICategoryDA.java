package pmcollection.dal.interfaces;

import pmcollection.be.Category;

import java.util.List;

public interface ICategoryDA {
    Category createCategory(Category category) throws Exception;
    List<Category> getAllCategories() throws Exception;
    Category getCategory(int id) throws Exception;
    void updateCategory(Category category) throws Exception;
    void deleteCategory(Category category) throws Exception;
}
