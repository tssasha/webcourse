package dao;

import models.File;

public interface FileDAO {
    //File findById(int id);

    void save(File file);

    void delete(File file);
}
