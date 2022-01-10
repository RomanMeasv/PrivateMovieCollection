package pmcollection.dal.dao;

import pmcollection.be.Movie;
import pmcollection.dal.ConnectionManager;
import pmcollection.dal.interfaces.IMovieDA;

import java.sql.*;
import java.util.List;

public class MovieDAO implements IMovieDA {
    ConnectionManager cm;

    public MovieDAO() {
        this.cm = new ConnectionManager();
    }

    /*
    Checking if the movie name already exists is omitted since it is no longer a requirement
     */
    @Override
    public Movie createMovie(Movie movie) throws SQLException {
        try (Connection con = cm.getConnection()) {
            String sqlcommandInsert = "INSERT INTO Movie VALUES (?, ?, ?, ?);";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
            pstmtSelect.setString(1, movie.getName());
            pstmtSelect.setFloat(2, movie.getRating());
            pstmtSelect.setString(3, movie.getFilelink());
            pstmtSelect.setDate(4, Date.valueOf(movie.getLastview()));
            pstmtSelect.execute();
            ResultSet rs = pstmtSelect.getGeneratedKeys();
            while (rs.next()) {
                movie.setId(rs.getInt(1));
            }
        }
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    @Override
    public Movie getMovie(int id) throws Exception {
        Movie movieSearched = null;
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM Movie WHERE id=?;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            pstmtSelect.setInt(1, id);
            ResultSet rs = pstmtSelect.executeQuery();
            while (rs.next()) {
                movieSearched = new Movie(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("rating"),
                        rs.getString("filelink"),
                        rs.getDate("lastview").toLocalDate()
                );
            }
        }
        return movieSearched;
    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public void deleteMovie(Movie movie) {

    }
}
