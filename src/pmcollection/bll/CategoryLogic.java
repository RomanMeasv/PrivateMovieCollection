package pmcollection.bll;

import pmcollection.be.Category;
import pmcollection.dal.dao.CategoryDAO;
import pmcollection.dal.interfaces.ICategoryDA;

import java.util.List;

public class CategoryLogic {

    private ICategoryDA categoryDAO;

    public CategoryLogic(){
        categoryDAO = new CategoryDAO();
    }

    public List<Category> getAllCategories() throws Exception {
        return this.categoryDAO.getAllCategories();
    }

    public Category addCategory(Category category) throws Exception {
        return this.categoryDAO.createCategory(category);
    }

    public void update(Category selected) throws Exception {
        this.categoryDAO.updateCategory(selected);
    }

    public void delete(Category selected) throws Exception {
        this.categoryDAO.deleteCategory(selected);
    }
}
