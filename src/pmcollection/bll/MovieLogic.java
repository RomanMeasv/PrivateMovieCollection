package pmcollection.bll;

import pmcollection.be.Movie;
import pmcollection.dal.interfaces.IMovieDA;
import pmcollection.dal.dao.MovieDAO;

import java.util.List;

public class MovieLogic {
    private IMovieDA movieDAO;

    public MovieLogic(){
        movieDAO = new MovieDAO();
    }

    public List<Movie> getAllMovies(){
        return movieDAO.getAllMovies();
    }
}
