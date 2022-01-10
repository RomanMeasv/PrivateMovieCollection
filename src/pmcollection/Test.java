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
    public static void main(String[] args) throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        Category cat = categoryDAO.getCategory(2);
        System.out.println(cat.getName());
    }
}
