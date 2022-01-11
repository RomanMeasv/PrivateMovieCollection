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

    public void update(Movie selected, Movie response) throws Exception {
        this.movieDAO.updateMovie(response);
        this.catMovieDAO.unlinkMovieFromItsCategories(selected);
        this.catMovieDAO.linkMovieToItsCategories(response);
    }

    public void delete(Movie selected) throws Exception {
        catMovieDAO.unlinkMovieFromItsCategories(selected);
        this.movieDAO.deleteMovie(selected);
    }

    public List<Movie> filterMovies(String name, List<Category> categories, float min, float max) throws Exception {
        List<Movie> filteredMovies = getAllMovies();
        if (!name.isEmpty())
        {
            filteredMovies = filterMoviesByName(filteredMovies, name);
        }
        if (!categories.isEmpty())
        {
            filteredMovies = filterMoviesByCategory(filteredMovies, categories);
        }
        if (min != 0 || max != 0)
        {
            filteredMovies = filterMoviesByRating(filteredMovies, min, max);
        }
        return filteredMovies;
    }

    public List<Movie> filterMoviesByName(List<Movie> movies, String query)
    {
        return movies.stream().filter(movie -> movie.getName().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))).toList();
    }

    public List<Movie> filterMoviesByCategory(List<Movie> movies, List<Category> categories)
    {
        return movies.stream()
                .filter(movie -> movie.getCategories().containsAll(categories))
                .toList();
    }

    public List<Movie> filterMoviesByRating(List<Movie> movies, float min, float max)
    {
        return movies.stream()
                .filter(movie -> (min > 0 ? min : 0) <= movie.getRating())
                .filter(movie -> (max > 0 ? max : 10) >= movie.getRating())
                .toList();
    }
}
