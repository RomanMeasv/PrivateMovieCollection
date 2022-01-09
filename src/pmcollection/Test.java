package pmcollection;

import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.dal.dao.CategoryDAO;
import pmcollection.dal.dao.MovieDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws SQLException {
        Category c = new Category( "Punk");
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.createCategory(c);

        Movie m = new Movie("Foo", new ArrayList<>(), 1, LocalDate.of(2000,1,1), "link");
        m.addCategory(c);
        MovieDAO mDAO = new MovieDAO();
        mDAO.createMovie(m);
    }
}
