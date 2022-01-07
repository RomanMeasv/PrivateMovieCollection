package pmcollection.dal;

import pmcollection.be.Category;

import java.util.List;

public interface ICategoryDAO {
    int create();
    List<Category> read();
    void update();
    void delete();
}
