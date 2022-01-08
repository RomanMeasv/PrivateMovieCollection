package pmcollection;

import pmcollection.be.Movie;
import pmcollection.dal.dao.MovieDAO;
import pmcollection.dal.exceptions.MovieNameAlreadyExistsException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws IOException, SQLException, MovieNameAlreadyExistsException {
        Movie m = new Movie("Foo", new ArrayList<>(), 1, LocalDate.of(2000,1,1), "link");
        MovieDAO mDAO = new MovieDAO();
        mDAO.createMovie(m);
    }
}
