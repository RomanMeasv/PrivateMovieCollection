package pmcollection.dal.dao;

import pmcollection.be.Movie;
import pmcollection.dal.ConnectionManager;
import pmcollection.dal.exceptions.MovieNameAlreadyExistsException;
import pmcollection.dal.interfaces.IMovieDA;

import java.sql.*;
import java.util.List;

public class MovieDAO implements IMovieDA {
    ConnectionManager cm;

    @Override
    public Movie createMovie(Movie movie) throws SQLException, MovieNameAlreadyExistsException {
        Movie movieCreated=null;
        try (Connection con = con.getConnection()) {
            //checks if movie name exists, TODO: extract to method
            String sqlCheckSelect = "SELECT * FROM Movie WHERE name = ?";
            PreparedStatement pstCheckAuthor = con.prepareStatement(sqlCheckSelect);
            pstCheckAuthor.setString(1, movie.getName());
            ResultSet rsCheck = pstCheckAuthor.executeQuery();

            if(!rsCheck.isBeforeFirst()) {
                String sqlcommandInsert = "INSERT INTO Movie VALUES (?, ?, ?, ?);";
                PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
                pstmtSelect.setString(1,movie.getName());
                pstmtSelect.setFloat(2, movie.getRating());
                pstmtSelect.setString(3, movie.getFilelink());
                pstmtSelect.setDate(4, Date.valueOf(movie.getLastview()));
                pstmtSelect.execute();
                ResultSet rs = pstmtSelect.getGeneratedKeys();
                while(rs.next())
                {
                    movieCreated = movie;
                    movieCreated.setId(rs.getInt(1));
                }
            } else {
                throw new MovieNameAlreadyExistsException();
            }
        } catch (SQLException throwables) {
            throw new SQLException();
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
