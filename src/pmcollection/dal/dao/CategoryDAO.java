package pmcollection.dal.dao;

import pmcollection.be.Category;
import pmcollection.dal.ConnectionManager;
import pmcollection.dal.interfaces.ICategoryDA;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDA {
    private ConnectionManager cm;

    public CategoryDAO() {
        this.cm = new ConnectionManager();
    }

    @Override
    public List<Category> getAllCategories() throws Exception{
        List<Category> allCategories = new ArrayList<>();
        try(Connection con = cm.getConnection()){
            String sql = "SELECT * FROM Category";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"));
                allCategories.add(category);
            }
        }
        return allCategories;
    }

    @Override
    public Category createCategory(Category category) throws Exception {
        try(Connection con = cm.getConnection()){
            String sql = "INSERT INTO Category VALUES (?);";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()) {
                category.setId(rs.getInt(1));
            }
        }
        return category;
    }

    @Override
    public Category getCategory(int id) throws Exception {
        Category categorySearched = null;
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM Category WHERE id=?;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            pstmtSelect.setInt(1, id);
            ResultSet rs = pstmtSelect.executeQuery();
            while (rs.next()) {
                categorySearched = new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        }
        return categorySearched;
    }

    @Override
    public void updateCategory(Category category) throws Exception {
        int id = category.getId();
        String name = category.getName();

        try(Connection con = cm.getConnection()){
            String sql = "UPDATE Category SET name = ? WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(2, category.getId());

            statement.setString(1, category.getName());
            statement.execute();
        }
    }

    @Override
    public void deleteCategory(Category category) throws Exception {
        try (Connection con = cm.getConnection()) {
            String sql = "DELETE FROM Category WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, category.getId());
            statement.execute();
        }
    }
}
