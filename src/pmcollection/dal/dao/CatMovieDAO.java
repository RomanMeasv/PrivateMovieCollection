package pmcollection.dal.dao;

import pmcollection.be.Category;
import pmcollection.be.Movie;
import pmcollection.dal.ConnectionManager;
import pmcollection.dal.interfaces.ICatMovieDA;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CatMovieDAO implements ICatMovieDA {
    ConnectionManager cm;
    MovieDAO movieDAO;
    CategoryDAO categoryDAO;

    public CatMovieDAO() {
        this.cm = new ConnectionManager();
        this.movieDAO = new MovieDAO();
        this.categoryDAO = new CategoryDAO();
    }

    @Override
    public HashMap<Movie, Category> getAllCatMovies() throws Exception {
        HashMap<Movie, Category> allCatMovies = new HashMap();
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM CatMovie;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();
            while(rs.next())
            {
                allCatMovies.put(movieDAO.getMovie(rs.getInt("movieId")), categoryDAO.getCategory(rs.getInt("categoryId")));
            }
        }
        return allCatMovies;
    }

    @Override
    public List<Category> getCategoriesOfMovieById(int id) throws Exception {
        List<Category>  categoriesSearched = new ArrayList<>();
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM CatMovie WHERE movieId=?;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            pstmtSelect.setInt(1, id);
            ResultSet rs = pstmtSelect.executeQuery();
            while (rs.next()) {
                categoriesSearched.add(categoryDAO.getCategory(rs.getInt("categoryId")));
            }
        }
        return categoriesSearched;
    }

    /*
    Reads the movie objects categories and creates the link in the CatMovie table
     */
    public void createCategoryLinksToMovie(Movie movie) throws Exception {
        for (Category category : movie.getCategories()) {
            createCatMovie(movie, category);
        }
    }

    @Override
    public void createCatMovie(Movie movie, Category category) throws Exception {
        //Here if the category is added already the movie is not checked since it should be done in the Movie.addCategory() method
        try (Connection con = cm.getConnection()) {
            String sqlcommandInsert = "INSERT INTO CatMovie VALUES (?, ?);";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
            pstmtSelect.setInt(1, category.getId());
            pstmtSelect.setInt(2, movie.getId());
            pstmtSelect.execute();
        }
    }

    @Override
    public void deleteCatMovie(Movie movie, Category category) {

    }
}
