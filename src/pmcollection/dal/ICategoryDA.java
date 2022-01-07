package pmcollection.dal;

import pmcollection.be.Category;

import java.util.List;

public interface ICategoryDA {
    int create();
    List<Category> read();
    void update();
    void delete();
}
