package pmcollection.bll;

import pmcollection.be.Movie;
import pmcollection.dal.IMovieDA;
import pmcollection.dal.MovieDAO;

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
