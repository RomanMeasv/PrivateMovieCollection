package pmcollection.dal.dao;

import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.dal.ConnectionManager;
import pmcollection.dal.interfaces.IMovieDA;

import java.sql.*;
import java.util.List;

public class MovieDAO implements IMovieDA {
    ConnectionManager cm;
    CatMovieDAO catMovieDAO;

    public MovieDAO() {
        this.cm = new ConnectionManager();
        this.catMovieDAO = new CatMovieDAO();
    }

    /*
    Checking if the movie name already exists is omitted since it is no longer a requirement
     */
    @Override
    public Movie createMovie(Movie movie) throws SQLException {
        Movie movieCreated=null;
        Connection con = cm.getConnection();
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
}
