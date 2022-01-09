package pmcollection.dal.dao;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.dal.ConnectionManager;
import pmcollection.dal.interfaces.ICatMovieDA;

import java.sql.*;
import java.util.HashMap;

public class CatMovieDAO implements ICatMovieDA {
    ConnectionManager cm;

    public CatMovieDAO() {
        this.cm = new ConnectionManager();
    }

    @Override
    public HashMap<Movie, Category> getAllCatMovies() {
        return null;
    }

    @Override
    public void createCatMovie(Movie movie, Category category) throws SQLException {
        //Here if the category is added already the movie is not checked since it should be done in the Movie.addCategory() method
        Connection con = cm.getConnection();
        String sqlcommandInsert = "INSERT INTO CatMovie VALUES (?, ?);";
        PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
        pstmtSelect.setInt(1,category.getId());
        pstmtSelect.setInt(2, movie.getId());
        pstmtSelect.execute();
    }

    @Override
    public void deleteCatMovie(Movie movie, Category category) {

    }
}
