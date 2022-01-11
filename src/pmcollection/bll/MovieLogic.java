package pmcollection.bll;

import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.dal.dao.CatMovieDAO;
import pmcollection.dal.interfaces.ICatMovieDA;
import pmcollection.dal.interfaces.IMovieDA;
import pmcollection.dal.dao.MovieDAO;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieLogic {
    private IMovieDA movieDAO;
    private ICatMovieDA catMovieDAO;

    public MovieLogic() throws Exception {
        movieDAO = new MovieDAO();
        catMovieDAO = new CatMovieDAO();
    }

    public List<Movie> getAllMovies() throws Exception {
        List<Movie> allMovies = movieDAO.getAllMovies();
        for (Movie movie : allMovies) {
            movie.setCategories(catMovieDAO.getCategoriesOfMovie(movie));
        }
        return allMovies;
    }

    public Movie getMovie(int id) throws Exception {
        Movie movie = movieDAO.getMovie(id);
        movie.setCategories(catMovieDAO.getCategoriesOfMovie(movie));
        return movie;
    }

    public Movie addMovie(Movie movie) throws Exception {
        Movie mov = this.movieDAO.createMovie(movie);
        catMovieDAO.linkMovieToItsCategories(movie);
        return mov;
    }

    public void update(Movie selected) throws Exception {
        this.movieDAO.updateMovie(selected);
        this.catMovieDAO.unlinkMovieFromItsCategories(selected);
        this.catMovieDAO.linkMovieToItsCategories(selected);
    }

    public void delete(Movie selected) throws Exception {
        catMovieDAO.unlinkMovieFromItsCategories(selected);
        this.movieDAO.deleteMovie(selected);
    }

    public List<Movie> filteredMovies(String nameFilterString, String categoryFilterString, String ratingFilterString) throws Exception {
        List<Movie> filteredMovies = getAllMovies();
        if (!nameFilterString.isEmpty())
        {
            filteredMovies = filterMoviesByName(filteredMovies, nameFilterString);
        }
        if (!categoryFilterString.isEmpty())
        {
            filteredMovies = filterMoviesByCategory(filteredMovies, categoryFilterString);
        }
        if (!ratingFilterString.isEmpty())
        {
            filteredMovies = filterMoviesByRating(filteredMovies, ratingFilterString);
        }
        return filteredMovies;
    }

    public List<Movie> filterMoviesByName(List<Movie> movies, String query)
    {
        return movies.stream().filter(movie -> movie.getName().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))).toList();
    }

    public List<Movie> filterMoviesByCategory(List<Movie> movies, String query)
    {
        return movies.stream().filter(movie -> movie.getCategories().toString().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))).toList();
    }

    public List<Movie> filterMoviesByRating(List<Movie> movies, String query)
    {
        if (query.split("-").length == 2)
        {
            int from = Integer.parseInt(query.split("-")[0]);
            int to = Integer.parseInt(query.split("-")[1]);
            return movies.stream().filter(movie -> movie.getRating() >= from && movie.getRating() <= to).toList();
        }
        return movies;
    }
}
