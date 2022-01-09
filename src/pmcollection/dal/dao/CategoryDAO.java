package pmcollection.dal.dao;

import pmcollection.be.Category;
import pmcollection.dal.ConnectionManager;
import pmcollection.dal.interfaces.ICategoryDA;

import java.sql.*;
import java.util.List;

public class CategoryDAO implements ICategoryDA {
    ConnectionManager cm;

    public CategoryDAO() {
        this.cm = new ConnectionManager();
    }

    @Override
    public Category createCategory(Category category) throws SQLException {
        Category categoryCreated = null;

        Connection con = cm.getConnection();
        String sqlcommandInsert = "INSERT INTO Category VALUES (?);";
        PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
        pstmtSelect.setString(1, category.getName());
        pstmtSelect.execute();

        ResultSet rs = pstmtSelect.getGeneratedKeys();
        while(rs.next()) {
            categoryCreated = category;
            categoryCreated.setId(rs.getInt(1));
        }

        return categoryCreated;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getCategory(int id) {
        return null;
    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public void deleteCategory(Category category) {

    }
}
