package pmcollection;

import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.bll.MovieLogic;
import pmcollection.dal.dao.CatMovieDAO;
import pmcollection.dal.dao.CategoryDAO;
import pmcollection.dal.dao.MovieDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception {
        MovieLogic movieDAO = new MovieLogic();

        for (Category cat : movieDAO.getMovie(5).getCategories())
        {
            System.out.println(cat.getName());
        }
    }
}
