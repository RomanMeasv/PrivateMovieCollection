package pmcollection.bll;

import pmcollection.be.Category;
import pmcollection.bll.exceptions.CatMovieException;
import pmcollection.bll.exceptions.CategoryException;
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

    public List<Category> getAllCategories() throws CategoryException {
        try
        { return this.categoryDAO.getAllCategories(); }
            catch (Exception e) { throw new CategoryException("Could not access movies from database!", e); }
    }

    public Category addCategory(Category category) throws CategoryException {
        try
        { return this.categoryDAO.createCategory(category); }
            catch (Exception e) { throw new CategoryException("Could not create category \"" + category.getName() + "\" in the database!", e); }
    }

    public void update(Category category) throws CategoryException {
        try
        { this.categoryDAO.updateCategory(category); }
            catch (Exception e) { throw new CategoryException("Could not update category \"" + category.getName() + "\" in the database!", e); }
    }

    public void delete(Category category) throws CatMovieException, CategoryException {
        try
        { catMovieDAO.unlinkCategoryFromMovies(category); }
            catch (Exception e) { throw new CatMovieException("Could not unlink category \"" + category.getName() + "\" from movie it was assigned to in the database!", e); }
        try
        { this.categoryDAO.deleteCategory(category); }
            catch (Exception e) { throw new CategoryException("Category \"" + category.getName() + "\" was unlinked from the movies it was assigned to, but the category itself could not be deleted from the database!", e); }
    }
}
