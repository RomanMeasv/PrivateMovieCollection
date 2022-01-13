package pmcollection.bll;

import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.bll.exceptions.CatMovieException;
import pmcollection.bll.exceptions.MovieException;
import pmcollection.bll.exceptions.MovieFilterException;
import pmcollection.bll.exceptions.BadOldMovieException;
import pmcollection.dal.dao.CatMovieDAO;
import pmcollection.dal.interfaces.ICatMovieDA;
import pmcollection.dal.interfaces.IMovieDA;
import pmcollection.dal.dao.MovieDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieLogic {
    private IMovieDA movieDAO;
    private ICatMovieDA catMovieDAO;

    public MovieLogic() {
        movieDAO = new MovieDAO();
        catMovieDAO = new CatMovieDAO();
    }

    public List<Movie> getAllMovies() throws MovieException, CatMovieException {
        List<Movie> allMovies;
        try
        { allMovies = new ArrayList<>(movieDAO.getAllMovies()); }
            catch (Exception e) { throw new MovieException("Could not access movies from database!", e); }
        for (Movie movie : allMovies) {
            try {
            movie.setCategories(catMovieDAO.getCategoriesOfMovie(movie)); }
                catch (Exception e) { throw new CatMovieException("Failed to retrieve categories of the movie \"" + movie.getName() + "\"", e); }
        }
        return allMovies;
    }

    /*
    public Movie getMovie(int id) {
        Movie movie = movieDAO.getMovie(id);
        movie.setCategories(catMovieDAO.getCategoriesOfMovie(movie));
        return movie;
    }
    */

    public Movie addMovie(Movie movie) throws MovieException, CatMovieException {
        Movie newMovie;
        try
        { newMovie = this.movieDAO.createMovie(movie); }
            catch (Exception e) { throw new MovieException("Failed to create movie \"" + movie.getName() + "\" in in database!", e); }
        try
        { catMovieDAO.linkMovieToItsCategories(movie); }
            catch (Exception e) { throw new CatMovieException("Movie \"" + movie.getName() + "\" was created but couldn't be linked to it's category(ies) in the database!", e); }
        return newMovie;
    }

    public void update(Movie selected, Movie response) throws MovieException, CatMovieException {
        try
        { this.movieDAO.updateMovie(response); }
            catch (Exception e) { throw new MovieException("Could not update movie \"" + selected.getName() + "\" in database!", e); }
        try
        { this.catMovieDAO.unlinkMovieFromItsCategories(selected);
          this.catMovieDAO.linkMovieToItsCategories(response); }
            catch (Exception e) { throw new CatMovieException("Could not edit category links of movie \"" + response.getName() + "\" in database!", e); }

    }

    public void delete(Movie movie) throws CatMovieException, MovieException {
        try
        { catMovieDAO.unlinkMovieFromItsCategories(movie); }
            catch (Exception e) {throw new CatMovieException("Could not unlink movie \"" + movie.getName() + "\" from it's categories in database!", e); }
        try
        { this.movieDAO.deleteMovie(movie); }
            catch (Exception e) {throw new MovieException("Movie \"" + movie.getName() + "\" was unlinked from it's categories but could not be deleted from the database!", e); }
    }

    public List<Movie> filterMovies(String name, List<Category> categories, float min, float max) throws MovieFilterException {
        List<Movie> filteredMovies;
        try
        { filteredMovies = getAllMovies(); }
            catch (Exception e) {throw new MovieFilterException("Failed to retrieve movies to be filtered!", e); }
        try
        { if (!name.isEmpty())
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
        } }
            catch (Exception e) {throw new MovieFilterException("Failed to apply filters to the movies!", e); }
        return filteredMovies;
    }

    private List<Movie> filterMoviesByName(List<Movie> movies, String query)
    {
        return movies.stream().filter(movie -> movie.getName().toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))).toList();
    }

    private List<Movie> filterMoviesByCategory(List<Movie> movies, List<Category> categories)
    {
        return movies.stream()
                .filter(movie -> movie.getCategories().containsAll(categories))
                .toList();
    }

    private List<Movie> filterMoviesByRating(List<Movie> movies, float min, float max)
    {
        return movies.stream()
                .filter(movie -> (min > 0 ? min : 0) <= movie.getRating())
                .filter(movie -> (max > 0 ? max : 10) >= movie.getRating())
                .toList();
    }

    public List<Movie> getBadOldMovies() throws BadOldMovieException {
        List<Movie> oldMovies;
        try
        { oldMovies = getAllMovies(); }
        catch (Exception e) {throw new BadOldMovieException("Could not get movies to check if bad and old ones are present!", e); }
        return oldMovies.stream()
                .filter(movie -> movie.getLastview().isBefore(LocalDate.now().minusYears(2)) && movie.getRating() < 6)
                .toList();
    }

}
