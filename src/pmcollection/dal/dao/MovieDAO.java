package pmcollection.dal.dao;

import pmcollection.be.Movie;
import pmcollection.dal.ConnectionManager;
import pmcollection.dal.interfaces.IMovieDA;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements IMovieDA {
    private ConnectionManager cm;

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
    public List<Movie> getAllMovies() throws Exception {
        List<Movie> allMovies = new ArrayList();
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM Movie;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();
            while(rs.next())
            {
                allMovies.add(getMovie(rs.getInt("id")));
            }
        }
        return allMovies;
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
    public void updateMovie(Movie movie) throws Exception {
        try (Connection con = cm.getConnection()) {
            String sqlcommandUpdate = "UPDATE Movie SET name = ?, rating = ?, filelink = ?, lastview = ? WHERE id = ?;";
            PreparedStatement pstmtUpdate = con.prepareStatement(sqlcommandUpdate);

            pstmtUpdate.setString(1,movie.getName());
            pstmtUpdate.setFloat(2, movie.getRating());
            pstmtUpdate.setString(3, movie.getFilelink());
            pstmtUpdate.setDate(4, Date.valueOf(movie.getLastview()));
            pstmtUpdate.setInt(5, movie.getId());
            pstmtUpdate.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws Exception{
        try (Connection con = cm.getConnection()) {
            String sql = "DELETE FROM Movie WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, movie.getId());
            statement.execute();
        }
    }
}
