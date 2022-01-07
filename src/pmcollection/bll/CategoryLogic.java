package pmcollection.bll;

import pmcollection.be.Category;
import pmcollection.dal.CategoryDAO;
import pmcollection.dal.ICategoryDA;

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
