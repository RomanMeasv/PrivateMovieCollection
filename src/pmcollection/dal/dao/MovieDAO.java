package pmcollection.dal.dao;

import pmcollection.be.Movie;
import pmcollection.dal.ConnectionManager;
import pmcollection.dal.interfaces.IMovieDA;

import java.sql.*;
import java.util.List;

public class MovieDAO implements IMovieDA {
    ConnectionManager cm;

    @Override
    public Movie createMovie(Movie movie) throws SQLException {
        Movie movieCreated=null;
        try (Connection con = con.getConnection()) {
            //I check if an author with the same name already exists in the databse,
            //if it does, I return the author already existing, if not, I create a new author
            //with this name and return the author created
            //No need to pass the name in lower case for a thorough check as sql Server seems to return the value as
            //intented disregarding the state of the CASE used.
            String sqlCheckSelect = "SELECT * FROM Movie WHERE name = ?";
            PreparedStatement pstCheckAuthor = con.prepareStatement(sqlCheckSelect);
            pstCheckAuthor.setString(1, movie.getName());
            ResultSet rsCheck = pstCheckAuthor.executeQuery();
            if(!rsCheck.isBeforeFirst()) {
                String sqlcommandInsert = "INSERT INTO Movie VALUES (?);";
                PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
                pstmtSelect.setString(1,movie.getName());
                pstmtSelect.execute();
                ResultSet rs = pstmtSelect.getGeneratedKeys();
                while(rs.next())
                {
                    movieCreated = new Movie(
                            rs.getInt(1),
                            movie.getName(),
                            movie.getRating(),
                            movie.getFilelink(),
                            movie.getLastview()
                    );
                }
            } else {
                while(rsCheck.next()) {
                    authorCreated= new Author(
                            rsCheck.getInt("id"),
                            rsCheck.getString("name")
                    );
                }
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
