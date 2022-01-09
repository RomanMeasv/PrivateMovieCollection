package pmcollection.dal.dao;

import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.dal.ConnectionManager;
import pmcollection.dal.exceptions.MovieNameAlreadyExistsException;
import pmcollection.dal.interfaces.IMovieDA;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class MovieDAO implements IMovieDA {
    ConnectionManager cm;
    CatMovieDAO catMovieDAO;

    public MovieDAO() {
        this.cm = new ConnectionManager();
        this.catMovieDAO = new CatMovieDAO();
    }

    @Override
    public Movie createMovie(Movie movie) throws SQLException, MovieNameAlreadyExistsException {
        Movie movieCreated=null;
        Connection con = cm.getConnection();
        if(!isMovieNameInDatabase(con, movie.getName())) {
            String sqlcommandInsert = "INSERT INTO Movie VALUES (?, ?, ?, ?);";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
            pstmtSelect.setString(1,movie.getName());
            pstmtSelect.setFloat(2, movie.getRating());
            pstmtSelect.setString(3, movie.getFilelink());
            pstmtSelect.setDate(4, Date.valueOf(movie.getLastview()));
            pstmtSelect.execute();
            ResultSet rs = pstmtSelect.getGeneratedKeys();
            while(rs.next()) {
                movieCreated = movie;
                movieCreated.setId(rs.getInt(1));
            }

            //.getCategories() should not produce NullPointerException as we agreed that at least one category should always be assigned in the UI
            for (Category category : movieCreated.getCategories())
            {
                catMovieDAO.createCatMovie(movieCreated, category);
            }
        } else {
            throw new MovieNameAlreadyExistsException();
        }
        return movieCreated;
    }

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    @Override
    public Movie getMovie(int id) {
        return null;
    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public void deleteMovie(Movie movie) {

    }

    /*
    Checks if the movie name is in the database
    return true if it is, false if it is not
     */
    private boolean isMovieNameInDatabase(Connection con, String name) throws SQLException {
        String sqlCheckSelect = "SELECT * FROM Movie WHERE name = ?";
        PreparedStatement pstCheckAuthor = con.prepareStatement(sqlCheckSelect);
        pstCheckAuthor.setString(1, name);
        ResultSet rsCheck = pstCheckAuthor.executeQuery();
        return rsCheck.isBeforeFirst();
    }
}
