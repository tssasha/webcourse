package dao;

import models.File;

import java.util.List;

public interface FileDAO {

    void save(File file);

    void delete(File file);

    List<File> findAllFilesInMessage(int no);
}
