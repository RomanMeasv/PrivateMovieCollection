package pmcollection.bll;

import pmcollection.be.Category;
import pmcollection.dal.dao.CatMovieDAO;
import pmcollection.dal.dao.CategoryDAO;
import pmcollection.dal.interfaces.ICatMovieDA;
import pmcollection.dal.interfaces.ICategoryDA;

import java.util.List;

public class CategoryLogic {

    private ICategoryDA categoryDAO;
    private ICatMovieDA catMovieDAO;

    public CategoryLogic(){
        categoryDAO = new CategoryDAO();
        catMovieDAO = new CatMovieDAO();
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
        catMovieDAO.unlinkCategoryFromMovies(selected);
        this.categoryDAO.deleteCategory(selected);
    }
}
