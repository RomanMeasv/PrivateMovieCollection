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
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}
