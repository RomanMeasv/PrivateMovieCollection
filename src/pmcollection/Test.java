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
        MovieDAO movieDAO = new MovieDAO();
        Movie m = movieDAO.getMovie(5);
        m.setName("Boo");
        movieDAO.updateMovie(m);
    }
}
